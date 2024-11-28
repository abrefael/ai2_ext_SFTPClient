package com.abrefael.sftpfileupload;

import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;

public class SFTPUploadExtension extends AndroidNonvisibleComponent implements Component {
    private SFTPFileUpload sftpClient;

    public SFTPUploadExtension(ComponentContainer container) {
        super(container.$form());
        sftpClient = new SFTPFileUpload(container.$form());
    }

    public void ConnectToSFTP(String host, int port, String username, String password) {
        boolean result = sftpClient.connect(host, port, username, password);
        EventDispatcher.dispatchEvent(this, "SFTPConnected", result);
    }

    public void UploadFile(String localFilePath, String remoteFilePath) {
        boolean result = sftpClient.uploadFile(localFilePath, remoteFilePath);
        EventDispatcher.dispatchEvent(this, "FileUploaded", result);
    }

    public void Disconnect() {
        sftpClient.disconnect();
    }
}
