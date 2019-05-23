package net.Client;

import bean.Constant;
import net.base.Csocket;
import net.Json.FileJson;
import net.base.Message;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FileClient {
    private static FileClient instance;

    public static FileClient getInstance() {
        return instance;
    }

    public FileClient() throws IOException {
        instance = this;
    }

    /**
     * 上传文件
     * @param fileJson 文件信息
     * @return 上传结果
     * @throws IOException
     */
    public synchronized Message upload(FileJson fileJson) {
        try {
            var socket = new Csocket(new Socket(Constant.HOST, Constant.FILEPROT));
            var file = new File(fileJson.path);
            if (!file.exists()) {
                return null;
            }
            fileJson.name = file.getName();
            fileJson.length = file.length();
            fileJson.time= Calendar.getInstance().getTimeInMillis();
            if (fileJson.owner == null) fileJson.owner = "-1";
            socket.writeUTF(new Message(0, 0, fileJson.toString()).toString());

            var fin = new FileInputStream(file);
            var bytes = new byte[1024];
            var length = fin.read(bytes, 0, bytes.length);
            while (length != -1) {
                socket.write(bytes);
                length = fin.read(bytes, 0, bytes.length);
            }
            socket.closeWriter();
            var result = Message.parse(socket.readUTF());
            socket.closeALL();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 下载文件
     * @param fileid 文件id
     * @param path 要存储到哪个文件夹
     * @return
     */
    public synchronized Message download(int fileid, String path) {
        return download(fileid, path, false);
    }

    private List<DownloadListener> downloadListeners = new ArrayList<>();

    public void addDownloadListener(DownloadListener listener, boolean clearDownloadListener) {
        if (clearDownloadListener) downloadListeners.clear();
        downloadListeners.add(listener);
    }

    public synchronized Message download(int fileid, String path, boolean nameById) {
        try {
            var fileJson = new FileJson();
            fileJson.fileId = fileid;
            var socket = new Csocket(new Socket(Constant.HOST, Constant.FILEPROT));
            socket.writeUTF(new Message(1, 0, fileJson.toString()).toString());
            var msg = Message.parse(socket.readUTF());
            if (msg.code < 0) {
                socket.closeALL();
                return null;
            }

            createDirs(path);

            var file = FileJson.parse(msg.props);
            path += "\\" + (nameById ? file.fileId : file.name);

            var fout = new FileOutputStream(new File(path));
            var bytes = new byte[1024];
            var length = socket.read(bytes, bytes.length);
            var curProgress = (long)length;
            while (length != -1) {
                fout.write(bytes, 0, bytes.length);
                length = socket.read(bytes, bytes.length);
                for (var i : downloadListeners) {
                    i.onReceive(curProgress, file.length);
                }
                curProgress += length;
            }
            for (var i : downloadListeners) {
                i.onReceive(file.length, file.length);
            }
            fout.close();
            socket.closeALL();
            return new Message(0, 0, path);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean createDirs(String path) {
        return new File(path).mkdirs();
    }
}
