package com.azriazhar.robocode;

import java.awt.Color;

import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.Rules;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

/**
 * Scan for a robot, fire and travel to its last location if detected. Fire and ram on hit. This
 * robot uses a basic gun and radar movements.
 *
 * @author Mohamad Azri Azhar
 */
public class SimpleRobot extends Robot {

  @Override
  public void run() {
    paintRobot();
    setAdjustGunForRobotTurn(true);

    for (;;) {
      turnGunRight(1000);
    }
  }

  @Override
  public void onScannedRobot(ScannedRobotEvent e) {
    double degrees =
        Utils.normalRelativeAngleDegrees(getHeading() - getRadarHeading() + e.getBearing());

    turnGunRight(degrees);
    fire(Rules.MAX_BULLET_POWER * 10 / e.getDistance());
    turnRight(e.getBearing());
    ahead(e.getDistance());
  }

  @Override
  public void onHitByBullet(HitByBulletEvent e) {
    turnRight(90 - e.getBearing());
    ahead(100);
  }

  @Override
  public void onHitWall(HitWallEvent e) {
    turnRight(90 - e.getBearing());
    ahead(100);
  }

  @Override
  public void onHitRobot(HitRobotEvent e) {
    double degrees =
        Utils.normalRelativeAngleDegrees(getHeading() - getRadarHeading() + e.getBearing());

    turnGunRight(degrees);
    fire(Rules.MAX_BULLET_POWER);
    ahead(100);
  }

  private void paintRobot() {
    setBodyColor(Color.BLACK);
    setRadarColor(Color.YELLOW);
    setGunColor(Color.RED);
    setBulletColor(Color.RED);
  }
}
