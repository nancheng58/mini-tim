package net.Server.handler;

import bean.Constant;
import net.base.Csocket;
import net.Json.FileJson;
import DB.api.FileApi;
import net.base.Message;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Objects;

/**
 * @description:
 **/
public class FileMessageHandler extends MessageHandler {
    private Csocket socket;

    public void setSocket(Csocket socket) {
        this.socket = socket;
    }

    @Override
    public Message handleMessage(Message message) {
        if (socket == null) {
            return new Message(-128, 0, "出现错误");
        }
        if (message.code == 0) {
            return saveFile(FileJson.parse(message.props));
        } else if (message.code == 1) {
            return sendFile(FileJson.parse(message.props));
        }
        return null;
    }

    public Message sendFile(FileJson info) {
        try {
            var gf = FileApi.getFile(info);
            var file = FileJson.parse(gf.props);
            var fs = new File(file.path + "\\" + file.fileId);
            var fin = new FileInputStream(fs);
            file.length = fs.length();
            gf.props = file.toString();
            socket.writeUTF(gf.toString());
            var bytes = new byte[1024];
            var length = fin.read(bytes, 0, bytes.length);
            while (length != -1) {
                socket.write(bytes);
                length = fin.read(bytes, 0, length);
            }
            fin.close();
            socket.closeWriter();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Message saveFile(FileJson info) {

        var path = Constant.getServerFileSavePath();
        var file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        info.path = path;
        var upload = FileJson.parse(Objects.requireNonNull(FileApi.addFile(info)).props);
        try {
            var fout = new FileOutputStream(new File(path + "\\" + upload.fileId));
            var bytes = new byte[1024];
            var length = socket.read(bytes, bytes.length);
            while (length != -1) {
                fout.write(bytes, 0, bytes.length);
                length = socket.read(bytes, bytes.length);
            }
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Message(upload.fileId, 0,upload.toString());
    }
}
