package gestion.scolaire.service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;

@Service
public class FileUploade {

    private static final String SFTP_SERVER = "31.207.38.123";
    private static final int SFTP_PORT = 22;
    private static final String SFTP_USER = "ftpunigest";
    private static final String SFTP_PASSWORD = "unigest";
    int retryCount = 3;

    private Session createSession() throws JSchException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(SFTP_USER, SFTP_SERVER, SFTP_PORT);
        session.setPassword(SFTP_PASSWORD);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect(10000);
        return session;
    }

    public String uploadFileToFTP(Path filePath, String fileName) throws Exception {
        int retries = retryCount;

        while (retries > 0) {
            Session session = null;
            ChannelSftp sftp = null;
            try {
                session = createSession();
                Channel channel = session.openChannel("sftp");
                channel.connect();
                sftp = (ChannelSftp) channel;

                String remoteFilePath = "/var/www/api.lyuni-gest.com/files/" + fileName;
                try (InputStream inputStream = new FileInputStream(filePath.toFile())) {
                    sftp.put(inputStream, remoteFilePath);
                }

                return "sftp://" + SFTP_USER + "@" + SFTP_SERVER + remoteFilePath;
            } catch (JSchException | SftpException e) {
                retries--;
                if (retries == 0) throw new Exception("Erreur lors de la connexion SFTP : " + e.getMessage(), e);
            } finally {
                if (sftp != null && sftp.isConnected()) sftp.exit();
                if (session != null && session.isConnected()) session.disconnect();
            }
        }
        throw new Exception("Échec du téléchargement après plusieurs tentatives.");
    }

    public byte[] getFileByName(String fileName) throws IOException {
        int retries = retryCount;
        while (retries > 0) {
            Session session = null;
            ChannelSftp sftp = null;
            try {
                session = createSession();
                Channel channel = session.openChannel("sftp");
                channel.connect();
                sftp = (ChannelSftp) channel;

                String remoteFilePath = "/var/www/api.lyuni-gest.com/files/" + fileName;
                try (InputStream inputStream = sftp.get(remoteFilePath)) {
                    return inputStream.readAllBytes();
                }
            } catch (JSchException | SftpException e) {
                retries--;
                if (retries == 0) throw new IOException("Erreur lors de la récupération SFTP : " + e.getMessage(), e);
            } finally {
                if (sftp != null && sftp.isConnected()) sftp.exit();
                if (session != null && session.isConnected()) session.disconnect();
            }
        }
        throw new IOException("Échec de récupération du fichier après plusieurs tentatives.");
    }

    // Méthodes similaires pour les images ou audios : 
   
}
