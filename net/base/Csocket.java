package net.base;
import java.io.*;
import java.net.Socket;

public class Csocket {
    private Socket socket;
    private DataInputStream reader;
    private DataOutputStream writer;

    public Csocket(Socket socket) throws IOException {
        this.socket = socket;
        reader = new DataInputStream(socket.getInputStream());
        writer = new DataOutputStream(socket.getOutputStream());
    }
    public Csocket(String host, int port) throws IOException {
        this(new Socket(host, port));
    }

    public String readUTF() throws IOException {
        return reader.readUTF();
    }

    public void writeUTF(String line) throws IOException {
        writer.writeUTF(line);
        writer.flush();
    }

    public void write(byte[] bytes) throws IOException {
        writer.write(bytes);
        writer.flush();
    }

    public int read(byte[] bytes, int length) throws IOException {
        return reader.read(bytes, 0, length);
    }

    public void closeReader() throws IOException {
        socket.shutdownInput();
    }

    public void closeWriter() throws IOException {
        socket.shutdownOutput();
    }

    public void closeALL() throws IOException {
        reader.close();
        writer.close();
        socket.close();
    }

    public void close() throws IOException {
        socket.close();
    }
}
