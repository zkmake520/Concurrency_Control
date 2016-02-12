# Concurrency_Control
An interesting concurrency_control problem implemented by java <br/>
 <br/>
Description:There is a bridge  with cars coming from two directions. The bridge allows maximum three cars to across it from same direction at same time. To balance two sides, after three cars acrossing the bridge, if there are cars waiting for the bridge on another side, these cars should have the higher priority. If there is no car waiting on another side, the following cars will have same priority.
<br/>
Assume cars on part A are from left to right, and cars on part B are on reverse direction.<br/>
Some boundary cases:
 1. C_a1 across the bridge, and there is no cars on both side.
 2. C_a1 across the bridge, and there is car waiting on another side but no coming car from same side.
 3. C_a1 across the bridge, and there is car coming from same side
 4. C_a3 (the third car from same side)across the bridge, and there is car waiting on both sides
 5. C_a3 (the third car from same side)across the bridge, and there is no car waiting on another side
 6. C_a4 (when there are already three cars on bridge, or only one or two cars on bridge)
