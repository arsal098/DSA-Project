class AkinatorEngine {

    Node root;
    Node current;

    void build() {

        root = new Node("Is it a living thing?");

        root.yes = new Node("Is it a human?");
        root.no = new Node("Is it man-made?");

        root.yes.yes = new Node("Is it a famous person?");
        root.yes.no = new Node("Is it an animal?");

        root.no.yes = new Node("Is it electronic?");
        root.no.no = new Node("Is it natural object?");

        root.yes.yes.yes = new Node("Is it a sports person?");
        root.yes.yes.no = new Node("Is it a scientist?");

        root.yes.no.yes = new Node("Dog");
        root.yes.no.no = new Node("Cat");

        root.no.yes.yes = new Node("Mobile Phone");
        root.no.yes.no = new Node("Laptop");

        root.no.no.yes = new Node("Mountain");
        root.no.no.no = new Node("River");

        root.yes.yes.yes.yes = new Node("Cristiano Ronaldo");
        root.yes.yes.yes.no = new Node("Lionel Messi");

        root.yes.yes.no.yes = new Node("Albert Einstein");
        root.yes.yes.no.no = new Node("Isaac Newton");

        current = root;
    }

    void reset() {
        current = root;
    }

    boolean isEnd(Node n) {
        return n.yes == null && n.no == null;
    }
}