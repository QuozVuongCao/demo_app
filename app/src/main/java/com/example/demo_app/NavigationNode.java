package com.example.demo_app;

import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Publisher;
import geometry_msgs.PoseStamped;

public class NavigationNode extends AbstractNodeMain {
    private Publisher<PoseStamped> publisher;

    @Override
    public GraphName getDefaultNodeName() {
        return GraphName.of("android/navigation");
    }

    @Override
    public void onStart(ConnectedNode connectedNode) {
        publisher = connectedNode.newPublisher("move_base_simple/goal", PoseStamped._TYPE);
    }

    public void sendGoal(double x, double y, double z, double w) {
        if (publisher != null) {
            PoseStamped pose = publisher.newMessage();
            pose.getHeader().setFrameId("map");
            pose.getPose().getPosition().setX(x);
            pose.getPose().getPosition().setY(y);
            pose.getPose().getOrientation().setZ(z);
            pose.getPose().getOrientation().setW(w);
            publisher.publish(pose);
        }
    }
}
