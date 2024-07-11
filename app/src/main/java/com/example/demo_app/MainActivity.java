package com.example.demo_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.ros.address.InetAddressFactory;
import org.ros.android.NodeMainActivity;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;

public class MainActivity extends NodeMainActivity {

    private TeleopNode teleopNode;
    private NavigationNode navigationNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teleopNode = new TeleopNode();
        navigationNode = new NavigationNode();

        Button buttonForward = findViewById(R.id.button_forward);
        buttonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teleopNode.sendVelocityCommand(1.0f, 0.0f); // Forward command
            }
        });

        Button buttonGoal = findViewById(R.id.button_goal);
        buttonGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationNode.sendGoal(2.0, 3.0, 0.0, 1.0); // Set goal position
            }
        });
    }

    @Override
    protected void init(NodeMainExecutor nodeMainExecutor) {
        NodeConfiguration nodeConfiguration = NodeConfiguration.newPublic(InetAddressFactory.newNonLoopback().getHostAddress());
        nodeConfiguration.setMasterUri(getMasterUri());

        nodeMainExecutor.execute(teleopNode, nodeConfiguration.setNodeName("android/teleop"));
        nodeMainExecutor.execute(navigationNode, nodeConfiguration.setNodeName("android/navigation"));
    }
}
