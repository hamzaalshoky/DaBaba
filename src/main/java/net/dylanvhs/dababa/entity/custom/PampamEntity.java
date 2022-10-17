package net.dylanvhs.dababa.entity.custom;

import net.dylanvhs.dababa.entity.variant.DaBabaVariant;
import net.dylanvhs.dababa.item.ModItems;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.Team;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class PampamEntity extends TamableAnimal implements IAnimatable{

    private AnimationFactory factory = new AnimationFactory(this);


    private static final EntityDataAccessor<Integer> EAT_COUNTER = SynchedEntityData.defineId(PampamEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> CROWNED = SynchedEntityData.defineId(PampamEntity.class, EntityDataSerializers.BOOLEAN);
    private static final int EAT_TICK_INTERVAL = 5;
    boolean gotBamboo;
    private boolean pampamDance;
    private BlockPos jukeboxPosition;

    static final Predicate<ItemEntity> PAMPAM_ITEMS = (p_29133_) -> {
        ItemStack itemstack = p_29133_.getItem();
        return (itemstack.is(Blocks.BAMBOO.asItem()) || itemstack.is(Blocks.CAKE.asItem())) && p_29133_.isAlive() && !p_29133_.hasPickUpDelay();
    };

    public PampamEntity(EntityType<? extends TamableAnimal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }

    public static AttributeSupplier setAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0D)
                .add(Attributes.ATTACK_DAMAGE, 1.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.3f).build();
    }

    public static void dance(LevelAccessor world, BlockPos pos, boolean dancing) {
        for(DaBabaEntity DaBaba : world.getEntitiesOfClass(DaBabaEntity.class, (new AABB(pos)).inflate(4.0D)))
            DaBaba.party(pos, dancing);
    }


    public void party(BlockPos pos, boolean isPartying) {
        // A separate method, due to setPartying being side-only.
        jukeboxPosition = pos;
        pampamDance = isPartying;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void setRecordPlayingNearby(@Nonnull BlockPos pos, boolean isPartying) {
        party(pos, isPartying);
    }

    public boolean isDancing() {
        return pampamDance;
    }



    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(6, (new HurtByTargetGoal(this)).setAlertOthers());
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.BAMBOO), false));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.0D, Ingredient.of(Blocks.BAMBOO.asItem()), false));
    }

    // ANIMATIONS //

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, 0.15F, 1.0F);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.FOX_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.DOLPHIN_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.DOLPHIN_DEATH;
    }

    protected float getSoundVolume() {
        return 0.2F;
    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("pampam.walk", true));
            return PlayState.CONTINUE;
        }

        if (this.isEating()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("pampam.eat", true));
            return PlayState.CONTINUE;
        }

        if (this.isDancing()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("pampam.dance", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("pampam.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    // VARIANTS //

    @Override
    public void readAdditionalSaveData(CompoundTag p_21815_) {
        super.readAdditionalSaveData(p_21815_);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(EAT_COUNTER, 0);
        this.entityData.define(CROWNED, false);
    }

    // TAMABILITY //
    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        Item itemForTaming = Items.BAMBOO;
        Item itemForCrown = ModItems.FLOWER_CROWN.get();

        if (item == itemForTaming && !isTame()) {
            if (this.level.isClientSide) {
                return InteractionResult.CONSUME;
            } else {
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                if (!ForgeEventFactory.onAnimalTame(this, player)) {
                    if (!this.level.isClientSide) {
                        super.tame(player);
                        this.navigation.recomputePath();
                        this.setTarget(null);
                        this.level.broadcastEntityEvent(this, (byte)7);
                        this.eat(true);
                    }
                }

                return InteractionResult.SUCCESS;
            }
        }else if(item == itemForCrown){
            setCrowned(true);
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
        }else if (this.isFood(itemstack)) {
            if (this.getTarget() != null) {
                this.gotBamboo = true;
            }

            if (this.isBaby()) {
                this.usePlayerItem(player,hand, itemstack);
                this.ageUp((int)((float)(-this.getAge() / 20) * 0.1F), true);
                this.gameEvent(GameEvent.MOB_INTERACT, this.eyeBlockPosition());
            } else if (!this.level.isClientSide && this.getAge() == 0 && this.canFallInLove()) {
                this.usePlayerItem(player,hand, itemstack);
                this.setInLove(player);
                this.gameEvent(GameEvent.MOB_INTERACT, this.eyeBlockPosition());
            } else {
                if (this.level.isClientSide || this.isInWater()) {
                    return InteractionResult.PASS;
                }

                this.eat(true);
                ItemStack itemstack1 = this.getItemBySlot(EquipmentSlot.MAINHAND);
                if (!itemstack1.isEmpty() && !player.getAbilities().instabuild) {
                    this.spawnAtLocation(itemstack1);
                }

                this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(itemstack.getItem(), 1));
                this.usePlayerItem(player,hand, itemstack);
            }

            return InteractionResult.SUCCESS;
        }
        if (itemstack.getItem() == itemForTaming) {
            return InteractionResult.PASS;
        }

        return super.mobInteract(player, hand);
    }

    @Override
    public void setTame(boolean tamed) {
        super.setTame(tamed);
    }

    @Override
    public Team getTeam() {
        return super.getTeam();
    }

    public boolean canBeLeashed(Player player) {
        return false;
    }

    public boolean isEating() {
        return this.entityData.get(EAT_COUNTER) > 0;
    }

    public void eat(boolean p_29217_) {
        this.entityData.set(EAT_COUNTER, p_29217_ ? 1 : 0);
    }

    private int getEatCounter() {
        return this.entityData.get(EAT_COUNTER);
    }

    private void setEatCounter(int p_29215_) {
        this.entityData.set(EAT_COUNTER, p_29215_);
    }

    private void handleEating() {
        if (!this.isEating() && !this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty() && this.random.nextInt(80) == 1) {
            this.eat(true);
        } else if (this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
            this.eat(false);
        }

        if (this.isEating()) {
            this.addEatingParticles();
            if (!this.level.isClientSide && this.getEatCounter() > 80 && this.random.nextInt(20) == 1) {
                if (this.getEatCounter() > 100 && this.isFoodOrCake(this.getItemBySlot(EquipmentSlot.MAINHAND))) {
                    if (!this.level.isClientSide) {
                        this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                        this.gameEvent(GameEvent.EAT, this.eyeBlockPosition());
                        this.navigation.stop();
                    }
                }

                this.eat(false);
                return;
            }

            this.setEatCounter(this.getEatCounter() + 1);
        }

    }

    private void addEatingParticles() {
        if (this.getEatCounter() % 5 == 0) {
            this.playSound(SoundEvents.PANDA_EAT, 0.5F + 0.5F * (float)this.random.nextInt(2), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);

            for(int i = 0; i < 6; ++i) {
                Vec3 vec3 = new Vec3(((double)this.random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, ((double)this.random.nextFloat() - 0.5D) * 0.1D);
                vec3 = vec3.xRot(-this.getXRot() * ((float)Math.PI / 180F));
                vec3 = vec3.yRot(-this.getYRot() * ((float)Math.PI / 180F));
                double d0 = (double)(-this.random.nextFloat()) * 0.6D - 0.3D;
                Vec3 vec31 = new Vec3(((double)this.random.nextFloat() - 0.5D) * 0.8D, d0, 1.0D + ((double)this.random.nextFloat() - 0.5D) * 0.4D);
                vec31 = vec31.yRot(-this.yBodyRot * ((float)Math.PI / 180F));
                vec31 = vec31.add(this.getX(), this.getEyeY() + 1.0D, this.getZ());
                this.level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getItemBySlot(EquipmentSlot.MAINHAND)), vec31.x, vec31.y, vec31.z, vec3.x, vec3.y + 0.05D, vec3.z);
            }
        }

    }

    public boolean isFood(ItemStack p_29192_) {
        return p_29192_.is(Blocks.BAMBOO.asItem());
    }

    private boolean isFoodOrCake(ItemStack p_29196_) {
        return this.isFood(p_29196_) || p_29196_.is(Blocks.CAKE.asItem());
    }

    public void tick(){
        super.tick();
        this.handleEating();
    }

    protected void pickUpItem(ItemEntity p_29121_) {
        if (this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty() && PAMPAM_ITEMS.test(p_29121_)) {
            this.onItemPickup(p_29121_);
            ItemStack itemstack = p_29121_.getItem();
            this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
            this.handDropChances[EquipmentSlot.MAINHAND.getIndex()] = 2.0F;
            this.take(p_29121_, itemstack.getCount());
            p_29121_.discard();
        }

    }

    public boolean canTakeItem(ItemStack p_29146_) {
        EquipmentSlot equipmentslot = Mob.getEquipmentSlotForItem(p_29146_);
        if (!this.getItemBySlot(equipmentslot).isEmpty()) {
            return false;
        } else {
            return equipmentslot == EquipmentSlot.MAINHAND && super.canTakeItem(p_29146_);
        }
    }

    public void setCrowned(boolean crowned) {
        this.entityData.set(CROWNED, crowned);
    }

    public boolean isCrowned() {
        return this.entityData.get(CROWNED);
    }
}
