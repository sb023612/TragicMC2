package tragicneko.tragicmc.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelYeti2 extends ModelBase
{
  //fields
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer nose;
    ModelRenderer rightfist;
    ModelRenderer leftfist;
    ModelRenderer rightear;
    ModelRenderer leftear;
    ModelRenderer leftupperear;
    ModelRenderer rightupperear;
    ModelRenderer lefteartip;
    ModelRenderer righteartip;
    ModelRenderer lefteyebrow;
    ModelRenderer righteyebrow;
    ModelRenderer rightwhisker;
    ModelRenderer leftwhisker;
    ModelRenderer upperchest;
    ModelRenderer lowerchest;
    ModelRenderer rightshoulder;
    ModelRenderer leftshoulder;
    ModelRenderer lowerbody;
    ModelRenderer rightfoot;
    ModelRenderer leftfoot;
    ModelRenderer crownbit1;
    ModelRenderer crownbit2;
    ModelRenderer crownbit3;
    ModelRenderer crownbit4;
    ModelRenderer crownbit5;
    ModelRenderer thumbclaw;
    ModelRenderer middleclaw;
    ModelRenderer indexclaw;
    ModelRenderer ringclaw;
    ModelRenderer pinkyclaw;
    ModelRenderer thumbclaw2;
    ModelRenderer indexclaw2;
    ModelRenderer middleclaw2;
    ModelRenderer ringclaw2;
    ModelRenderer pinkyclaw2;
    ModelRenderer footclaw1;
    ModelRenderer footclaw2;
    ModelRenderer footclaw3;
    ModelRenderer footclaw4;
  
  public ModelYeti2()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      head = new ModelRenderer(this, 0, 0);
      head.addBox(-5F, -8F, -4F, 10, 8, 6);
      head.setRotationPoint(0F, -7F, 0F);
      head.setTextureSize(128, 64);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      body = new ModelRenderer(this, 70, 0);
      body.addBox(-8F, 0F, -2F, 16, 12, 6);
      body.setRotationPoint(0F, -7F, 1F);
      body.setTextureSize(128, 64);
      body.mirror = true;
      setRotation(body, 0.2082002F, 0F, 0F);
      rightarm = new ModelRenderer(this, 48, 10);
      rightarm.addBox(-3F, 0F, -2F, 4, 12, 5);
      rightarm.setRotationPoint(-9F, -7F, 1F);
      rightarm.setTextureSize(128, 64);
      rightarm.mirror = true;
      setRotation(rightarm, 0F, 0F, 0F);
      leftarm = new ModelRenderer(this, 48, 10);
      leftarm.addBox(0F, 0F, -2F, 4, 12, 5);
      leftarm.setRotationPoint(8F, -7F, 1F);
      leftarm.setTextureSize(128, 64);
      leftarm.mirror = true;
      setRotation(leftarm, 0F, 0F, 0F);
      rightleg = new ModelRenderer(this, 0, 16);
      rightleg.addBox(-2F, 0F, -2F, 4, 10, 4);
      rightleg.setRotationPoint(-4F, 10F, 5F);
      rightleg.setTextureSize(128, 64);
      rightleg.mirror = true;
      setRotation(rightleg, 0F, 0F, 0F);
      leftleg = new ModelRenderer(this, 0, 16);
      leftleg.addBox(-2F, 0F, -2F, 4, 10, 4);
      leftleg.setRotationPoint(4F, 10F, 5F);
      leftleg.setTextureSize(128, 64);
      leftleg.mirror = true;
      setRotation(leftleg, 0F, 0F, 0F);
      nose = new ModelRenderer(this, 32, 0);
      nose.addBox(-2F, -6F, -5F, 4, 2, 1);
      nose.setRotationPoint(0F, -7F, 0F);
      nose.setTextureSize(128, 64);
      nose.mirror = true;
      setRotation(nose, 0F, 0F, 0F);
      rightfist = new ModelRenderer(this, 0, 36);
      rightfist.addBox(-4F, 12F, -3F, 6, 10, 8);
      rightfist.setRotationPoint(-9F, -7F, 1F);
      rightfist.setTextureSize(128, 64);
      rightfist.mirror = true;
      setRotation(rightfist, 0F, 0F, 0F);
      leftfist = new ModelRenderer(this, 32, 36);
      leftfist.addBox(-1F, 12F, -3F, 6, 10, 8);
      leftfist.setRotationPoint(8F, -7F, 1F);
      leftfist.setTextureSize(128, 64);
      leftfist.mirror = true;
      setRotation(leftfist, 0F, 0F, 0F);
      rightear = new ModelRenderer(this, 48, 0);
      rightear.addBox(-6F, -6F, -2F, 1, 3, 3);
      rightear.setRotationPoint(0F, -7F, 0F);
      rightear.setTextureSize(128, 64);
      rightear.mirror = true;
      setRotation(rightear, 0F, 0F, 0F);
      leftear = new ModelRenderer(this, 48, 0);
      leftear.addBox(5F, -6F, -2F, 1, 3, 3);
      leftear.setRotationPoint(0F, -7F, 0F);
      leftear.setTextureSize(128, 64);
      leftear.mirror = true;
      setRotation(leftear, 0F, 0F, 0F);
      leftupperear = new ModelRenderer(this, 32, 3);
      leftupperear.addBox(0F, 0F, 0F, 1, 1, 2);
      leftupperear.setRotationPoint(-6F, -14F, 0F);
      leftupperear.setTextureSize(128, 64);
      leftupperear.mirror = true;
      setRotation(leftupperear, 0F, 0F, 0F);
      rightupperear = new ModelRenderer(this, 32, 3);
      rightupperear.addBox(0F, 0F, 0F, 1, 1, 2);
      rightupperear.setRotationPoint(5F, -14F, 0F);
      rightupperear.setTextureSize(128, 64);
      rightupperear.mirror = true;
      setRotation(rightupperear, 0F, 0F, 0F);
      lefteartip = new ModelRenderer(this, 0, 0);
      lefteartip.addBox(0F, 0F, 0F, 1, 1, 1);
      lefteartip.setRotationPoint(5F, -15F, 2F);
      lefteartip.setTextureSize(128, 64);
      lefteartip.mirror = true;
      setRotation(lefteartip, 0F, 0F, 0F);
      righteartip = new ModelRenderer(this, 0, 0);
      righteartip.addBox(0F, 0F, 0F, 1, 1, 1);
      righteartip.setRotationPoint(-6F, -15F, 2F);
      righteartip.setTextureSize(128, 64);
      righteartip.mirror = true;
      setRotation(righteartip, 0F, 0F, 0F);
      lefteyebrow = new ModelRenderer(this, 32, 12);
      lefteyebrow.addBox(0F, 0F, 0F, 4, 1, 1);
      lefteyebrow.setRotationPoint(1F, -14F, -5F);
      lefteyebrow.setTextureSize(128, 64);
      lefteyebrow.mirror = true;
      setRotation(lefteyebrow, 0F, 0.0594858F, -0.4461433F);
      righteyebrow = new ModelRenderer(this, 32, 12);
      righteyebrow.addBox(0F, 0F, 0F, 4, 1, 1);
      righteyebrow.setRotationPoint(-2F, -13F, -5F);
      righteyebrow.setTextureSize(128, 64);
      righteyebrow.mirror = true;
      setRotation(righteyebrow, 0F, 0F, -2.706603F);
      rightwhisker = new ModelRenderer(this, 32, 7);
      rightwhisker.addBox(0F, 0F, 0F, 1, 3, 1);
      rightwhisker.setRotationPoint(-3F, -7F, -4F);
      rightwhisker.setTextureSize(128, 64);
      rightwhisker.mirror = true;
      setRotation(rightwhisker, 0F, 0F, 0F);
      leftwhisker = new ModelRenderer(this, 32, 7);
      leftwhisker.addBox(0F, 0F, 0F, 1, 3, 1);
      leftwhisker.setRotationPoint(2F, -7F, -4F);
      leftwhisker.setTextureSize(128, 64);
      leftwhisker.mirror = true;
      setRotation(leftwhisker, 0F, 0F, 0F);
      upperchest = new ModelRenderer(this, 76, 30);
      upperchest.addBox(-7F, 0F, 0F, 14, 6, 1);
      upperchest.setRotationPoint(0F, -4F, -2F);
      upperchest.setTextureSize(128, 64);
      upperchest.mirror = true;
      setRotation(upperchest, 0.2082002F, 0F, 0F);
      lowerchest = new ModelRenderer(this, 76, 38);
      lowerchest.addBox(-5F, 0F, -0.7733333F, 10, 6, 1);
      lowerchest.setRotationPoint(0F, 1F, 0F);
      lowerchest.setTextureSize(128, 64);
      lowerchest.mirror = true;
      setRotation(lowerchest, 0.2082002F, 0F, 0F);
      rightshoulder = new ModelRenderer(this, 66, 46);
      rightshoulder.addBox(-1F, -1F, -3F, 7, 7, 7);
      rightshoulder.setRotationPoint(9F, -7F, 1F);
      rightshoulder.setTextureSize(128, 64);
      rightshoulder.mirror = true;
      setRotation(rightshoulder, 0F, 0F, 0F);
      leftshoulder = new ModelRenderer(this, 66, 46);
      leftshoulder.addBox(-6F, -1F, -3F, 7, 7, 7);
      leftshoulder.setRotationPoint(-9F, -7F, 1F);
      leftshoulder.setTextureSize(128, 64);
      leftshoulder.mirror = true;
      setRotation(leftshoulder, 0F, 0F, 0F);
      lowerbody = new ModelRenderer(this, 73, 18);
      lowerbody.addBox(-6F, 9F, 0F, 12, 6, 5);
      lowerbody.setRotationPoint(0F, -4F, 0F);
      lowerbody.setTextureSize(128, 64);
      lowerbody.mirror = true;
      setRotation(lowerbody, 0.2082002F, 0F, 0F);
      rightfoot = new ModelRenderer(this, 17, 21);
      rightfoot.addBox(-3F, 8F, -6F, 5, 4, 8);
      rightfoot.setRotationPoint(-4F, 12F, 5F);
      rightfoot.setTextureSize(128, 64);
      rightfoot.mirror = true;
      setRotation(rightfoot, 0F, 0F, 0F);
      leftfoot = new ModelRenderer(this, 17, 21);
      leftfoot.addBox(-2F, 8F, -6F, 5, 4, 8);
      leftfoot.setRotationPoint(4F, 12F, 5F);
      leftfoot.setTextureSize(128, 64);
      leftfoot.mirror = true;
      setRotation(leftfoot, 0F, 0F, 0F);
      crownbit1 = new ModelRenderer(this, 0, 0);
      crownbit1.addBox(-5F, -10F, -2F, 1, 2, 1);
      crownbit1.setRotationPoint(0F, -7F, 0F);
      crownbit1.setTextureSize(128, 64);
      crownbit1.mirror = true;
      setRotation(crownbit1, 0F, 0F, 0F);
      crownbit2 = new ModelRenderer(this, 0, 0);
      crownbit2.addBox(4F, -10F, -2F, 1, 2, 1);
      crownbit2.setRotationPoint(0F, -7F, 0F);
      crownbit2.setTextureSize(128, 64);
      crownbit2.mirror = true;
      setRotation(crownbit2, 0F, 0F, 0F);
      crownbit3 = new ModelRenderer(this, 0, 0);
      crownbit3.addBox(-3F, -11F, -3F, 1, 3, 1);
      crownbit3.setRotationPoint(0F, -7F, 0F);
      crownbit3.setTextureSize(128, 64);
      crownbit3.mirror = true;
      setRotation(crownbit3, 0F, 0F, 0F);
      crownbit4 = new ModelRenderer(this, 0, 0);
      crownbit4.addBox(2F, -11F, -3F, 1, 3, 1);
      crownbit4.setRotationPoint(0F, -7F, 0F);
      crownbit4.setTextureSize(128, 64);
      crownbit4.mirror = true;
      setRotation(crownbit4, 0F, 0F, 0F);
      crownbit5 = new ModelRenderer(this, 0, 0);
      crownbit5.addBox(-1F, -10F, -4F, 2, 2, 1);
      crownbit5.setRotationPoint(0F, -7F, 0F);
      crownbit5.setTextureSize(128, 64);
      crownbit5.mirror = true;
      setRotation(crownbit5, 0F, 0F, 0F);
      thumbclaw = new ModelRenderer(this, 0, 0);
      thumbclaw.addBox(0F, 22F, 0F, 1, 2, 1);
      thumbclaw.setRotationPoint(-9F, -7F, 0F);
      thumbclaw.setTextureSize(128, 64);
      thumbclaw.mirror = true;
      setRotation(thumbclaw, 0F, 0F, 0F);
      middleclaw = new ModelRenderer(this, 0, 0);
      middleclaw.addBox(-3F, 22F, 1F, 1, 3, 1);
      middleclaw.setRotationPoint(-9F, -7F, 0F);
      middleclaw.setTextureSize(128, 64);
      middleclaw.mirror = true;
      setRotation(middleclaw, 0F, 0F, 0F);
      indexclaw = new ModelRenderer(this, 0, 0);
      indexclaw.addBox(-2F, 22F, -1F, 1, 3, 1);
      indexclaw.setRotationPoint(-9F, -7F, 0F);
      indexclaw.setTextureSize(128, 64);
      indexclaw.mirror = true;
      setRotation(indexclaw, 0F, 0F, 0F);
      ringclaw = new ModelRenderer(this, 0, 0);
      ringclaw.addBox(-3F, 22F, 3F, 1, 3, 1);
      ringclaw.setRotationPoint(-9F, -7F, 0F);
      ringclaw.setTextureSize(128, 64);
      ringclaw.mirror = true;
      setRotation(ringclaw, 0F, 0F, 0F);
      pinkyclaw = new ModelRenderer(this, 0, 0);
      pinkyclaw.addBox(-2F, 22F, 5F, 1, 2, 1);
      pinkyclaw.setRotationPoint(-9F, -7F, 0F);
      pinkyclaw.setTextureSize(128, 64);
      pinkyclaw.mirror = true;
      setRotation(pinkyclaw, 0F, 0F, 0F);
      thumbclaw2 = new ModelRenderer(this, 0, 0);
      thumbclaw2.addBox(-1F, 22F, 0F, 1, 2, 1);
      thumbclaw2.setRotationPoint(9F, -7F, 0F);
      thumbclaw2.setTextureSize(128, 64);
      thumbclaw2.mirror = true;
      setRotation(thumbclaw2, 0F, 0F, 0F);
      indexclaw2 = new ModelRenderer(this, 0, 0);
      indexclaw2.addBox(1F, 22F, -1F, 1, 3, 1);
      indexclaw2.setRotationPoint(9F, -7F, 0F);
      indexclaw2.setTextureSize(128, 64);
      indexclaw2.mirror = true;
      setRotation(indexclaw2, 0F, 0F, 0F);
      middleclaw2 = new ModelRenderer(this, 0, 0);
      middleclaw2.addBox(2F, 22F, 1F, 1, 3, 1);
      middleclaw2.setRotationPoint(9F, -7F, 0F);
      middleclaw2.setTextureSize(128, 64);
      middleclaw2.mirror = true;
      setRotation(middleclaw2, 0F, 0F, 0F);
      ringclaw2 = new ModelRenderer(this, 0, 0);
      ringclaw2.addBox(2F, 22F, 3F, 1, 3, 1);
      ringclaw2.setRotationPoint(9F, -7F, 0F);
      ringclaw2.setTextureSize(128, 64);
      ringclaw2.mirror = true;
      setRotation(ringclaw2, 0F, 0F, 0F);
      pinkyclaw2 = new ModelRenderer(this, 0, 0);
      pinkyclaw2.addBox(1F, 22F, 5F, 1, 2, 1);
      pinkyclaw2.setRotationPoint(9F, -7F, 0F);
      pinkyclaw2.setTextureSize(128, 64);
      pinkyclaw2.mirror = true;
      setRotation(pinkyclaw2, 0F, 0F, 0F);
      footclaw1 = new ModelRenderer(this, 0, 0);
      footclaw1.addBox(0F, 11F, -8F, 1, 3, 2);
      footclaw1.setRotationPoint(-4F, 10F, 5F);
      footclaw1.setTextureSize(128, 64);
      footclaw1.mirror = true;
      setRotation(footclaw1, 0F, 0F, 0F);
      footclaw2 = new ModelRenderer(this, 0, 0);
      footclaw2.addBox(-2F, 11F, -8F, 1, 3, 2);
      footclaw2.setRotationPoint(-4F, 10F, 5F);
      footclaw2.setTextureSize(128, 64);
      footclaw2.mirror = true;
      setRotation(footclaw2, 0F, 0F, 0F);
      footclaw3 = new ModelRenderer(this, 0, 0);
      footclaw3.addBox(-1F, 11F, -8F, 1, 3, 2);
      footclaw3.setRotationPoint(4F, 10F, 5F);
      footclaw3.setTextureSize(128, 64);
      footclaw3.mirror = true;
      setRotation(footclaw3, 0F, 0F, 0F);
      footclaw4 = new ModelRenderer(this, 0, 0);
      footclaw4.addBox(1F, 11F, -8F, 1, 3, 2);
      footclaw4.setRotationPoint(4F, 10F, 5F);
      footclaw4.setTextureSize(128, 64);
      footclaw4.mirror = true;
      setRotation(footclaw4, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    head.render(f5);
    body.render(f5);
    rightarm.render(f5);
    leftarm.render(f5);
    rightleg.render(f5);
    leftleg.render(f5);
    nose.render(f5);
    rightfist.render(f5);
    leftfist.render(f5);
    rightear.render(f5);
    leftear.render(f5);
    leftupperear.render(f5);
    rightupperear.render(f5);
    lefteartip.render(f5);
    righteartip.render(f5);
    lefteyebrow.render(f5);
    righteyebrow.render(f5);
    rightwhisker.render(f5);
    leftwhisker.render(f5);
    upperchest.render(f5);
    lowerchest.render(f5);
    rightshoulder.render(f5);
    leftshoulder.render(f5);
    lowerbody.render(f5);
    rightfoot.render(f5);
    leftfoot.render(f5);
    crownbit1.render(f5);
    crownbit2.render(f5);
    crownbit3.render(f5);
    crownbit4.render(f5);
    crownbit5.render(f5);
    thumbclaw.render(f5);
    middleclaw.render(f5);
    indexclaw.render(f5);
    ringclaw.render(f5);
    pinkyclaw.render(f5);
    thumbclaw2.render(f5);
    indexclaw2.render(f5);
    middleclaw2.render(f5);
    ringclaw2.render(f5);
    pinkyclaw2.render(f5);
    footclaw1.render(f5);
    footclaw2.render(f5);
    footclaw3.render(f5);
    footclaw4.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}