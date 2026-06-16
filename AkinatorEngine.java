import java.util.*;

class AkinatorEngine {

    Node root;
    Node current;

    ArrayList<Node> allNodes = new ArrayList<>();
    Stack<Node> history = new Stack<>();
    Queue<String> sessionLog = new LinkedList<>();
    HashMap<String, Node> map = new HashMap<>();
    PriorityQueue<Integer> confidence = new PriorityQueue<>(Collections.reverseOrder());

    void build() {

        root = new Node("Does it belong to living beings?");
        map.put("Q1", root);
        allNodes.add(root);

        root.yes = new Node("Is it a human being?");        // Q2
        root.no = new Node("Is it man-made object?");       // Q3

        // Living branch
        root.yes.yes = new Node("Is it a famous person?");  // Q4
        root.yes.no = new Node("Is it an animal?");         // Q5

        // Non-living branch
        root.no.yes = new Node("Is it electronic?");        // Q6
        root.no.no = new Node("Is it a natural object?");   // Q7

        // Famous people
        root.yes.yes.yes = new Node("Is it a sports person?");
        root.yes.yes.no = new Node("Is it a scientist?");

        root.yes.yes.yes.yes = new Node("Cristiano Ronaldo");
        root.yes.yes.yes.no = new Node("Lionel Messi");

        root.yes.yes.no.yes = new Node("Albert Einstein");
        root.yes.yes.no.no = new Node("Isaac Newton");

        // Animals
        root.yes.no.yes = new Node("Is it a pet?");
        root.yes.no.no = new Node("Is it a wild animal?");

        root.yes.no.yes.yes = new Node("Dog");
        root.yes.no.yes.no = new Node("Cat");

        root.yes.no.no.yes = new Node("Lion");
        root.yes.no.no.no = new Node("Elephant");

        // Non-living
        root.no.yes.yes = new Node("Is it a communication device?");
        root.no.yes.no = new Node("Is it a computing device?");

        root.no.yes.yes.yes = new Node("Mobile Phone");
        root.no.yes.yes.no = new Node("Smart Watch");

        root.no.yes.no.yes = new Node("Laptop");
        root.no.yes.no.no = new Node("Tablet");

        root.no.no.yes = new Node("Is it a planet?");
        root.no.no.no = new Node("Is it a landform?");

        root.no.no.yes.yes = new Node("Earth");
        root.no.no.yes.no = new Node("Mars");

        root.no.no.no.yes = new Node("Mountain");
        root.no.no.no.no = new Node("River");

        current = root;
    }

    void moveYes() {
        history.push(current);
        sessionLog.add(current.data + " -> YES");
        confidence.add(history.size());

        if (current.yes != null)
            current = current.yes;
    }

    void moveNo() {
        history.push(current);
        sessionLog.add(current.data + " -> NO");
        confidence.add(history.size());

        if (current.no != null)
            current = current.no;
    }

    void reset() {
        current = root;
        history.clear();
        sessionLog.clear();
        confidence.clear();
    }

    boolean isEnd(Node n) {
        return n.yes == null && n.no == null;
    }

    int getConfidence() {
        if (confidence.isEmpty()) return 0;
        return confidence.peek() * 10;
    }
}