package nodes;

import common.*;
import java.io.IOException;

// import src.common.*;

public class SuperNode extends Node {

    public SuperNode(int port_num) throws IOException {
        super(port_num);
        this.node_type = Utility.SUPER_NODE;
    }
}
