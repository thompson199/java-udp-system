package src.nodes;

import java.io.IOException;
import src.common.*;

public class BaseNode extends Node {

    public BaseNode(int port_num) throws IOException {
        super(port_num);
        this.node_type = Utility.BASE_NODE;
    }
}
