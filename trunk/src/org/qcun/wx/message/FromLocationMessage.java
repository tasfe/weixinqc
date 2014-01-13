package org.qcun.wx.message;

public class FromLocationMessage extends FromMessageMsgId
{
  private double location_X;
  private double location_Y;
  private int scale;
  private String label;

  public FromLocationMessage()
  {
    super.setMsgType("image");
  }

  public double getLocation_X()
  {
    return this.location_X;
  }

  void setLocation_X(double location_X) {
    this.location_X = location_X;
  }

  public double getLocation_Y() {
    return this.location_Y;
  }

  void setLocation_Y(double location_Y) {
    this.location_Y = location_Y;
  }

  public int getScale() {
    return this.scale;
  }

  void setScale(int scale) {
    this.scale = scale;
  }

  public String getLabel() {
    return this.label;
  }

  void setLabel(String label) {
    this.label = label;
  }
}