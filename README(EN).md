# JAVA-Course-Design-of-Software-College-of-ShanDong-University

## The experiment requires the design of a class affairs management system. The class members can check the announcement, vote and other operations.
## Functions of the final system
1. Privilege management: Administrators and users have two kinds of permissions. Administrators have log records of the operation process. Administrators have the right to issue announcements and votes.

2. Submitting votes: List the options of consent and disagreement. Each student who receives the votes can see the current status of the manuscript and add a suggestion column for supplementary comments. Voting status and details are displayed when voting.

3. Announcement Circulation: Announcement is sent to every member of the class and reminded. The bulletin information is displayed on the class bulletin board with underlined headings.

4. File sharing: On the server side, each class has a shared space, allowing class members to upload and download (offline). It realizes the preview of file attributes, including file name, size, upload time and uploader. The file can be opened directly after downloading.

5. Instant messaging: independent pop-up window, basic functions: a one-to-one dialogue (private chat), speaking to the class (group chat), can send pictures.

6. Emotional Sending: Implement the function of selecting an expression and sending it.

7. Screenshot function: Select and send screenshots, monitor the keyboard to achieve the combination key Ctrl + Alt + X fast screenshots function.

8. Jitter function: self-jitter and send jitter message, realize the function of both sides jitter together, private chat and group chat can be used. 9. System tray: realize the following functions: left-click pop-up window; right-click pop-up option menu, you can add classes, create classes, open, exit; when receiving messages, the tray flickers until the user clicks on the tray;

10. Message Prompt Voice: Send Message Prompt Voice when Receiving Message

11. Send message function, support picture sending. Monitor message text editor, monitor keyboard to realize the function of sending messages by pressing Enter key.

12. Drawing board: realizes the function of real-time hand-drawing and sends it to the current chat users or classes.

13. Avatar function: users and classes can add avatars. 14. Login registration function: verify the password is correct. A mechanism to prevent duplicate login is added. 15. Join the class, create the class function: you can create and join the class, create the class and automatically become an administrator.

16. Message reminder will change from Fig. 1 to Fig. 2 after receiving the message. Administrators publish manuscripts, announcements, voting will also be prompted. 17. Chat Bubble Function: Draw chat bubbles, distinguish message users from other users. Chat bubbles can place text and pictures.

18. Click on the avatar in the class to initiate a private chat.

19. Clicking on the avatar or username during a private conversation can display user information.

20. Add a small game function, and add a score statistics function, view user information can see the highest records of each other.

21. Realize the log function. Each time a user makes a request, the result is written to the log. Image

### 1. System Module Architecture System Architecture: C/S Architecture, realizing the separation of client and server. Usually the client runs the application program, the server runs the service program, and the application program submits an application to the service program. The service program analyses whether the application is reasonable, and decides whether to return data information or error instructions. It achieves the separation of chat, document and information service, and does not interfere with each other. Client: Client includes three parts: chat, file, information service and GUI of the system.

Server: Including server and database. The servers include chat, file and information service servers, which monitor three ports respectively. The database part includes database connection and database query API.
System module

There are five packages in the system. They are beans, DB, GUI, net, util. The system has 5 packages. There are five categories:

1. User class: Contains user information invocation methods.

2. Constant class: System static tool class, which contains the path of cache folders (files, chat records, screenshots, etc.) and the tool static method of getting file size.

3. Draw class: Drawing board function. By listening to the mouse drawing and calling the screen capture.
4. Emoticon class: facial expression function, draw a JLabel array, add facial expression to the array, by listening for each facial image.

5. Game 2048: 2048 mini-games, materials downloaded from the Internet, added the score statistics function, view user information can see the highest records of each other.

6. ScreenShot class: ScreenShot function, call the system's Toolkit. getDefaultToolkit (). getScreenSize () method to obtain full-screen screenshots, monitor the mouse to determine the location and save pictures to achieve this function.

7. TipMusic Class: Message prompt sound function, receiving the message to play the specified sound.

### 2. The GUI package is the system interface and component package. There is also a base package inside the package, which is a rewritten swing component and a rewritten font class to beautify the interface. In addition, there are eight user interface classes, namely login, registration, home page, announcement, voting, user details, class creation and class participation. And add message list component charbar and draw bubble component chatbubble.

### 3. Net package is the system communication package, which contains four packages: base, Client, Json and Server.

Base package is the communication base package shared by client and server, including Csocket class and Message class. The Csocket class is a rewritten socket tool, and the Message class is the basic protocol for transmission.

Json packages are packages related to JSON transformation. In data transmission, the system uses the form of JSON transformation to realize object <-> JSON string. The class inside the package implements the method of converting objects used to transfer messages to JSON and JSON to object.

Client packages are client-related packages. The package contains three clients: chat, file and information service, as well as interfaces for monitoring chat and file clients. The chat client realizes the function of sending and receiving chat information, the file client realizes the function of uploading and downloading files, and the information service client realizes the function of inquiry and feedback of information (such as login, registration, publication of announcements, etc.). There is also a client startup class, responsible for starting the client.

Server packages are server-related packages. There are two packages handler and Server inside the package. 1) The Server package contains three servers (chat, file, information service) and one Server Manager (responsible for starting three servers). All three servers realize the function of communication connection. 2) Each server is equipped with a client handler class (message transceiver) and a message handler in the handler package. Each time there is a client connection, the server opens a new client handler thread to receive messages, and processes them with message handler and returns the results to the client. 3) There is also a server startup class, which is responsible for starting the server.

### 4. DB packages are database related packages. It has database connection and database API, which is responsible for processing all data addition, search, modify, delete commands, and provide data acquisition methods.



### 5. Util package is the system material and picture beautification package, there are three internal categories. The PictureUtil class simplifies the way to call the image path. PictureUtil. class. getClassLoader (). getResource ("util/resource/image/"+name)) ImageUtil class is an image processing class that can compress images and create a circular mask. The RendererUtil class is a rendering class that creates image shadows.
