import java.util.*;

class GameState {
    Stack<Node> path = new Stack<>();
    Stack<String> answers = new Stack<>();
    int steps = 0;

    void reset() {
        path.clear();
        answers.clear();
        steps = 0;
    }

    void add(Node node, String ans) {
        path.push(node);
        answers.push(ans);
        steps++;
    }

    double confidence() {
        return Math.min(100, steps * 12.5);
    }
}