package com.example.demo_app;

import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Publisher;
import geometry_msgs.Twist;

public class TeleopNode extends AbstractNodeMain {
    private Publisher<Twist> publisher;

    @Override
    public GraphName getDefaultNodeName() {
        return GraphName.of("android/teleop");
    }

    @Override
    public void onStart(ConnectedNode connectedNode) {
        publisher = connectedNode.newPublisher("cmd_vel", Twist._TYPE);
    }

    public void sendVelocityCommand(float linearX, float angularZ) {
        if (publisher != null) {
            Twist twist = publisher.newMessage();
            twist.getLinear().setX(linearX);
            twist.getAngular().setZ(angularZ);
            publisher.publish(twist);
        }
    }
}
