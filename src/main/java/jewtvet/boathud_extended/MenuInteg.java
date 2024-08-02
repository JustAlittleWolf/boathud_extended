package jewtvet.boathud_extended;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public class MenuInteg implements ModMenuApi {
    private static final MutableText TITLE = Text.translatable("boathud.config.title");
    private static final MutableText CAT = Text.translatable("boathud.config.cat");
    private static final MutableText ENABLED = Text.translatable("boathud.option.enabled");
    private static final MutableText EXTENDED = Text.translatable("boathud.option.extended");
    private static final MutableText TELEMETRY = Text.translatable("boathud.option.telemetry");
    private static final MutableText TELEMETRY_ENABLED = Text.translatable("boathud.option.telemetry_enabled");
    private static final MutableText TELEMETRY_DIRECTORY = Text.translatable("boathud.option.telemetry_directory");
    private static final MutableText TELEMETRY_DIRECTORY_TOOLTIP = Text.translatable("boathud.tooltip.telemetry_directory");
    private static final MutableText CHECKPOINT = Text.translatable("boathud.option.checkpoint");
    private static final MutableText CHECKPOINT_ENABLED = Text.translatable("boathud.option.checkpoint_enabled");
    private static final MutableText CHECKPOINT_FILE = Text.translatable("boathud.option.checkpoint_file");
    private static final MutableText CHECKPOINT_FILE_TOOLTIP = Text.translatable("boathud.tooltip.checkpoint_file");
    private static final MutableText CIRCULAR_TRACK = Text.translatable("boathud.option.circular_track");
    private static final MutableText CIRCULAR_TRACK_TOOLTIP = Text.translatable("boathud.tooltip.circular_track");
    private static final MutableText BAR_TYPE = Text.translatable("boathud.option.bar_type");
    private static final MutableText SPEED_FORMAT = Text.translatable("boathud.option.speed_format");
    private static final MutableText ACCELERATION_FORMAT = Text.translatable("boathud.option.acceleration_format");
    private static final MutableText TIP_EXTENDED = Text.translatable("boathud.tooltip.extended");
    private static final MutableText TIP_BAR = Text.translatable("boathud.tooltip.bar_type");
    private static final MutableText TIP_BAR_PACKED = Text.translatable("boathud.tooltip.bar_type.packed");
    private static final MutableText TIP_BAR_MIXED = Text.translatable("boathud.tooltip.bar_type.mixed");
    private static final MutableText TIP_BAR_BLUE = Text.translatable("boathud.tooltip.bar_type.blue");
    private static final MutableText Y_OFFSET = Text.translatable("boathud.option.y_offset");
    private static final MutableText Y_OFFSET_TOOLTIP = Text.translatable("boathud.tooltip.y_offset");

    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (parent) -> {
            ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(TITLE);
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();
            ConfigCategory cat = builder.getOrCreateCategory(CAT);
            cat.addEntry(entryBuilder.startBooleanToggle(ENABLED, Config.enabled).setDefaultValue(true).setSaveConsumer((newVal) -> {
                Config.enabled = newVal;
            }).build()).addEntry(entryBuilder.startBooleanToggle(EXTENDED, Config.extended).setDefaultValue(true).setSaveConsumer((newVal) -> {
                Config.extended = newVal;
            }).setTooltip(new Text[]{TIP_EXTENDED}).build()).addEntry(entryBuilder.startIntSlider(Y_OFFSET, Config.yOffset, 0, 300).setDefaultValue(36).setSaveConsumer((newVal) -> {
                Config.yOffset = newVal;
            }).setTooltip(new Text[]{Y_OFFSET_TOOLTIP}).build()).addEntry(entryBuilder.startEnumSelector(BAR_TYPE, MenuInteg.BarType.class, MenuInteg.BarType.values()[Config.barType]).setDefaultValue(MenuInteg.BarType.PACKED).setTooltip(new Text[]{TIP_BAR, TIP_BAR_PACKED, TIP_BAR_MIXED, TIP_BAR_BLUE}).setSaveConsumer((newVal) -> {
                Config.barType = newVal.ordinal();
            }).setEnumNameProvider((value) -> {
                return Text.translatable("boathud.option.bar_type." + value.toString());
            }).build()).addEntry(entryBuilder.startEnumSelector(SPEED_FORMAT, MenuInteg.SpeedFormat.class, MenuInteg.SpeedFormat.values()[Config.speedType]).setDefaultValue(MenuInteg.SpeedFormat.MS).setSaveConsumer((newVal) -> {
                Config.setSpeedUnit(newVal.ordinal());
            }).setEnumNameProvider((value) -> {
                return Text.translatable("boathud.option.speed_format." + value.toString());
            }).build()).addEntry(entryBuilder.startEnumSelector(ACCELERATION_FORMAT, MenuInteg.AccelerationFormat.class, MenuInteg.AccelerationFormat.values()[Config.accelerationType]).setDefaultValue(MenuInteg.AccelerationFormat.MSS).setSaveConsumer((newVal) -> {
                Config.setAccelerationUnit(newVal.ordinal());
            }).setEnumNameProvider((value) -> {
                return Text.translatable("boathud.option.acceleration_format." + value.toString());
            }).build());
            SubCategoryBuilder telemetry = entryBuilder.startSubCategory(TELEMETRY).setExpanded(true);
            telemetry.add(entryBuilder.startBooleanToggle(TELEMETRY_ENABLED, Config.telemetryEnabled).setDefaultValue(false).setSaveConsumer((newVal) -> {
                Config.telemetryEnabled = newVal;
            }).build());
            telemetry.add(entryBuilder.startStrField(TELEMETRY_DIRECTORY, Config.telemetryDirectory).setDefaultValue("C:/boat_telemetry/").setSaveConsumer((newVal) -> {
                Config.telemetryDirectory = newVal;
            }).setTooltip(new Text[]{TELEMETRY_DIRECTORY_TOOLTIP}).build());
            cat.addEntry(telemetry.build());
            SubCategoryBuilder checkpoints = entryBuilder.startSubCategory(CHECKPOINT).setExpanded(true);
            checkpoints.add(entryBuilder.startBooleanToggle(CHECKPOINT_ENABLED, Config.checkpointEnabled).setDefaultValue(false).setSaveConsumer((newVal) -> {
                Config.checkpointEnabled = newVal;
            }).build());
            checkpoints.add(entryBuilder.startStrField(CHECKPOINT_FILE, Config.checkpointFile).setDefaultValue("C:/checkpoints.cf").setSaveConsumer((newVal) -> {
                Config.checkpointFile = newVal;
            }).setTooltip(new Text[]{CHECKPOINT_FILE_TOOLTIP}).build());
            checkpoints.add(entryBuilder.startBooleanToggle(CIRCULAR_TRACK, Config.circularTrack).setDefaultValue(false).setSaveConsumer((newVal) -> {
                Config.circularTrack = newVal;
            }).setTooltip(new Text[]{CIRCULAR_TRACK_TOOLTIP}).build());
            cat.addEntry(checkpoints.build());
            builder.setSavingRunnable(() -> {
                Config.save();
            });
            return builder.build();
        };
    }

    public static enum BarType {
        PACKED,
        MIXED,
        BLUE;

        // $FF: synthetic method
        private static MenuInteg.BarType[] $values() {
            return new MenuInteg.BarType[]{PACKED, MIXED, BLUE};
        }
    }

    public static enum SpeedFormat {
        MS,
        KMPH,
        MPH,
        KT;

        // $FF: synthetic method
        private static MenuInteg.SpeedFormat[] $values() {
            return new MenuInteg.SpeedFormat[]{MS, KMPH, MPH, KT};
        }
    }

    public static enum AccelerationFormat {
        MSS,
        G;

        // $FF: synthetic method
        private static MenuInteg.AccelerationFormat[] $values() {
            return new MenuInteg.AccelerationFormat[]{MSS, G};
        }
    }
}
