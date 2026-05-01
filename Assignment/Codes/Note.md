# CSE 434 PPDS Lab Assignment - Tasks

## Task 1: Observe the Current Behaviour

Run the server. Connect multiple clients. Send messages from different clients.

Answer the following questions:

- Can clients communicate with each other?
- What happens when multiple clients send messages at the same time?
- Is the server operator able to reply efficiently?

## Task 2: Remove Server-Side Manual Input

Modify the server so that:

- It does not use BufferedReader(System.in) anymore.
- The server does not manually type responses.

**Hint:** Remove or comment out: `br.readLine();`

## Task 3: Store Connected Clients

Add a shared list in the server to keep track of all connected clients.

**Hint:** `static ArrayList<ClientHandler> clients = new ArrayList<>();`
Add each new client to this list when they connect.

## Task 4: Broadcast Messages

Modify the ClientHandler so that:

- When a client sends a message, it is forwarded to all other clients.

**Expected behaviour:**

- Client 1 says: Hello
- Client 2 receives: Client 1: Hello
- Client 3 receives: Client 1: Hello

## Task 5: Handle Client Exit

Update the code so that:

- When a client types "exit", the connection closes properly.
- The client is removed from the shared list.

## Task 6: Improve Client Program

Modify the client so that:

- It can send and receive messages simultaneously.

**Hint:** Use one thread for sending. Use another thread for receiving.

## Task 7: (Optional Bonus) Add Client Names

Instead of using client numbers:

- Ask the user to enter a name when the client starts.
- Display messages like:
  - Alice: Hello
  - Bob: Hi!
