package i18nbase;

import i18nbase.objects.Service;
import i18nbase.objects.armor_gui.ArmorGUI;
import i18nbase.objects.armor_gui.ArmorGUI_DE;
import i18nbase.objects.armor_gui.ArmorGUI_FR;
import i18nbase.objects.armor_gui.ArmorGUI_RU;
import i18nbase.objects.buildings.Buildings;
import i18nbase.objects.buildings.Buildings_DE;
import i18nbase.objects.buildings.Buildings_FR;
import i18nbase.objects.buildings.Buildings_RU;
import i18nbase.objects.buildings_gui.BuildingsGUI;
import i18nbase.objects.buildings_gui.BuildingsGUI_DE;
import i18nbase.objects.buildings_gui.BuildingsGUI_FR;
import i18nbase.objects.buildings_gui.BuildingsGUI_RU;
import i18nbase.objects.common_gui.CommonGUI;
import i18nbase.objects.common_gui.CommonGUI_DE;
import i18nbase.objects.common_gui.CommonGUI_FR;
import i18nbase.objects.common_gui.CommonGUI_RU;
import i18nbase.objects.creatures.Creatures;
import i18nbase.objects.creatures.Creatures_DE;
import i18nbase.objects.creatures.Creatures_FR;
import i18nbase.objects.creatures.Creatures_RU;
import i18nbase.objects.creatures_gui.CreaturesGUI;
import i18nbase.objects.creatures_gui.CreaturesGUI_DE;
import i18nbase.objects.creatures_gui.CreaturesGUI_FR;
import i18nbase.objects.creatures_gui.CreaturesGUI_RU;
import i18nbase.objects.items.Items;
import i18nbase.objects.items.Items_DE;
import i18nbase.objects.items.Items_FR;
import i18nbase.objects.items.Items_RU;
import i18nbase.objects.itemsets.ItemSets;
import i18nbase.objects.itemsets.ItemSets_DE;
import i18nbase.objects.itemsets.ItemSets_FR;
import i18nbase.objects.itemsets.ItemSets_RU;
import i18nbase.objects.itemtypes.ItemTypes;
import i18nbase.objects.races.Races;
import i18nbase.objects.races.Races_DE;
import i18nbase.objects.races.Races_FR;
import i18nbase.objects.races.Races_RU;
import i18nbase.objects.skills_gui.SkillsGUI;
import i18nbase.objects.skills_gui.SkillsGUI_DE;
import i18nbase.objects.skills_gui.SkillsGUI_FR;
import i18nbase.objects.skills_gui.SkillsGUI_RU;
import i18nbase.objects.skillschoolmapping.SkillSchoolMapping;
import i18nbase.objects.skillssubschoolmapping.SkillSubSchoolMapping;
import i18nbase.objects.spells.Spells;
import i18nbase.objects.spells_gui.SpellsGUI;
import i18nbase.objects.spells_gui.SpellsGUI_DE;
import i18nbase.objects.spells_gui.SpellsGUI_FR;
import i18nbase.objects.spells_gui.SpellsGUI_RU;
import i18nbase.objects.units_gui.UnitsGUI;
import i18nbase.objects.units_gui.UnitsGUI_DE;
import i18nbase.objects.units_gui.UnitsGUI_FR;
import i18nbase.objects.units_gui.UnitsGUI_RU;
import i18nbase.objects.weapons_gui.WeaponsGUI;
import i18nbase.objects.weapons_gui.WeaponsGUI_DE;
import i18nbase.objects.weapons_gui.WeaponsGUI_FR;
import i18nbase.objects.weapons_gui.WeaponsGUI_RU;
import org.apache.log4j.Logger;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Mojo(name = "createDB", defaultPhase = LifecyclePhase.VALIDATE)
public class DatabaseCreationMojo extends AbstractMojo {

    private static final Logger LOGGER = Logger.getLogger(DatabaseCreationMojo.class);

    @Override
    public void execute() throws MojoExecutionException {
        String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        path = path.substring(0, path.lastIndexOf("/") + 1);
        try {
            String decodedPath = URLDecoder.decode(path, "UTF-8");
            Service.INSTANCE.setDatabasePath(decodedPath);
            createCommonGUITables();
            createArmorGUITables();
            createBuildingsTables();
            createBuildingsGUITables();
            createRacesTables();
            createCreaturesTables();
            createCreaturesGUITables();
            createItemsTables();
            createItemsSetsTables();
            createItemTypesTables();
            createSkillsGUITables();
            createSkillSchoolMappingTables();
            createSkillSubSchoolMappingTables();
            createSpellsTables();
            createSpellsGUITables();
            createUnitsGUITables();
            createWeaponsGUITables();
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private void createCommonGUITables() {
        LOGGER.info("Creating common GUI tables ... ");
        Service.INSTANCE.createTableFromResourceFile("common_gui.properties", CommonGUI.class);
        Service.INSTANCE.createTableFromResourceFile("common_gui_de.properties", CommonGUI_DE.class);
        Service.INSTANCE.createTableFromResourceFile("common_gui_fr.properties", CommonGUI_FR.class);
        Service.INSTANCE.createTableFromResourceFile("common_gui_ru.properties", CommonGUI_RU.class);
        LOGGER.info("Creating common GUI tables [complete]");
    }

    private void createArmorGUITables() {
        LOGGER.info("Creating armor GUI tables ... ");
        Service.INSTANCE.createTableFromResourceFile("armor_gui.properties", ArmorGUI.class);
        Service.INSTANCE.createTableFromResourceFile("armor_gui_de.properties", ArmorGUI_DE.class);
        Service.INSTANCE.createTableFromResourceFile("armor_gui_fr.properties", ArmorGUI_FR.class);
        Service.INSTANCE.createTableFromResourceFile("armor_gui_ru.properties", ArmorGUI_RU.class);
        LOGGER.info("Creating armor GUI tables [complete]");
    }

    private void createBuildingsGUITables() {
        LOGGER.info("Creating buildings GUI tables ... ");
        Service.INSTANCE.createTableFromResourceFile("buildings_gui.properties", BuildingsGUI.class);
        Service.INSTANCE.createTableFromResourceFile("buildings_gui_de.properties", BuildingsGUI_DE.class);
        Service.INSTANCE.createTableFromResourceFile("buildings_gui_fr.properties", BuildingsGUI_FR.class);
        Service.INSTANCE.createTableFromResourceFile("buildings_gui_ru.properties", BuildingsGUI_RU.class);
        LOGGER.info("Creating buildings GUI tables [complete]");
    }

    private void createBuildingsTables() {
        LOGGER.info("Creating buildings tables ... ");
        Service.INSTANCE.createTableFromResourceFile("buildings.properties", Buildings.class);
        Service.INSTANCE.createTableFromResourceFile("buildings_de.properties", Buildings_DE.class);
        Service.INSTANCE.createTableFromResourceFile("buildings_fr.properties", Buildings_FR.class);
        Service.INSTANCE.createTableFromResourceFile("buildings_ru.properties", Buildings_RU.class);
        LOGGER.info("Creating buildings tables [complete]");
    }

    private void createRacesTables() {
        LOGGER.info("Creating races tables ... ");
        Service.INSTANCE.createTableFromResourceFile("races.properties", Races.class);
        Service.INSTANCE.createTableFromResourceFile("races_de.properties", Races_DE.class);
        Service.INSTANCE.createTableFromResourceFile("races_fr.properties", Races_FR.class);
        Service.INSTANCE.createTableFromResourceFile("races_ru.properties", Races_RU.class);
        LOGGER.info("Creating races tables [complete]");
    }

    private void createCreaturesTables() {
        LOGGER.info("Creating creatures tables ... ");
        Service.INSTANCE.createTableFromResourceFile("creatures.properties", Creatures.class);
        Service.INSTANCE.createTableFromResourceFile("creatures_de.properties", Creatures_DE.class);
        Service.INSTANCE.createTableFromResourceFile("creatures_fr.properties", Creatures_FR.class);
        Service.INSTANCE.createTableFromResourceFile("creatures_ru.properties", Creatures_RU.class);
        LOGGER.info("Creating creatures tables [complete]");
    }

    private void createCreaturesGUITables() {
        LOGGER.info("Creating creatures GUI tables ... ");
        Service.INSTANCE.createTableFromResourceFile("creatures_gui.properties", CreaturesGUI.class);
        Service.INSTANCE.createTableFromResourceFile("creatures_gui_de.properties", CreaturesGUI_DE.class);
        Service.INSTANCE.createTableFromResourceFile("creatures_gui_fr.properties", CreaturesGUI_FR.class);
        Service.INSTANCE.createTableFromResourceFile("creatures_gui_ru.properties", CreaturesGUI_RU.class);
        LOGGER.info("Creating creatures GUI tables [complete]");
    }

    private void createItemsTables() {
        LOGGER.info("Creating items tables ... ");
        Service.INSTANCE.createTableFromResourceFile("items.properties", Items.class);
        Service.INSTANCE.createTableFromResourceFile("items_de.properties", Items_DE.class);
        Service.INSTANCE.createTableFromResourceFile("items_fr.properties", Items_FR.class);
        Service.INSTANCE.createTableFromResourceFile("items_ru.properties", Items_RU.class);
        LOGGER.info("Creating items tables [complete]");
    }

    private void createItemsSetsTables() {
        LOGGER.info("Creating item sets tables ... ");
        Service.INSTANCE.createTableFromResourceFile("itemSetsMapping.properties", ItemSets.class);
        Service.INSTANCE.createTableFromResourceFile("itemSetsMapping_de.properties", ItemSets_DE.class);
        Service.INSTANCE.createTableFromResourceFile("itemSetsMapping_fr.properties", ItemSets_FR.class);
        Service.INSTANCE.createTableFromResourceFile("itemSetsMapping_ru.properties", ItemSets_RU.class);
        LOGGER.info("Creating item sets tables [complete]");
    }

    private void createItemTypesTables() {
        LOGGER.info("Creating item types tables ... ");
        Service.INSTANCE.createTableFromResourceFile("itemTypesMapping.properties", ItemTypes.class);
        LOGGER.info("Creating item types tables [complete]");
    }

    private void createSkillsGUITables() {
        LOGGER.info("Creating skills GUI tables ... ");
        Service.INSTANCE.createTableFromResourceFile("skills_gui.properties", SkillsGUI.class);
        Service.INSTANCE.createTableFromResourceFile("skills_gui_de.properties", SkillsGUI_DE.class);
        Service.INSTANCE.createTableFromResourceFile("skills_gui_fr.properties", SkillsGUI_FR.class);
        Service.INSTANCE.createTableFromResourceFile("skills_gui_ru.properties", SkillsGUI_RU.class);
        LOGGER.info("Creating skills GUI tables [complete]");
    }

    private void createSkillSchoolMappingTables() {
        LOGGER.info("Creating skill school mapping tables ... ");
        Service.INSTANCE.createTableFromResourceFile("skillSchoolMapping.properties", SkillSchoolMapping.class);
        LOGGER.info("Creating skill school mapping tables [complete]");
    }

    private void createSkillSubSchoolMappingTables() {
        LOGGER.info("Creating skill sub school mapping tables ... ");
        Service.INSTANCE.createTableFromResourceFile("skillSubSchoolMapping.properties", SkillSubSchoolMapping.class);
        LOGGER.info("Creating skill sub school mapping tables [complete]");
    }

    private void createSpellsTables() {
        LOGGER.info("Creating spells tables ... ");
        Service.INSTANCE.createTableFromResourceFile("spells.properties", Spells.class);
        LOGGER.info("Creating spells tables [complete]");
    }

    private void createSpellsGUITables() {
        LOGGER.info("Creating spells GUI tables ... ");
        Service.INSTANCE.createTableFromResourceFile("spells_gui.properties", SpellsGUI.class);
        Service.INSTANCE.createTableFromResourceFile("spells_gui_de.properties", SpellsGUI_DE.class);
        Service.INSTANCE.createTableFromResourceFile("spells_gui_fr.properties", SpellsGUI_FR.class);
        Service.INSTANCE.createTableFromResourceFile("spells_gui_ru.properties", SpellsGUI_RU.class);
        LOGGER.info("Creating spells GUI tables [complete]");
    }

    private void createUnitsGUITables() {
        LOGGER.info("Creating units GUI tables ... ");
        Service.INSTANCE.createTableFromResourceFile("units_gui.properties", UnitsGUI.class);
        Service.INSTANCE.createTableFromResourceFile("units_gui_de.properties", UnitsGUI_DE.class);
        Service.INSTANCE.createTableFromResourceFile("units_gui_fr.properties", UnitsGUI_FR.class);
        Service.INSTANCE.createTableFromResourceFile("units_gui_ru.properties", UnitsGUI_RU.class);
        LOGGER.info("Creating units GUI tables [complete]");
    }

    private void createWeaponsGUITables() {
        LOGGER.info("Creating weapons GUI tables ... ");
        Service.INSTANCE.createTableFromResourceFile("weapon_gui.properties", WeaponsGUI.class);
        Service.INSTANCE.createTableFromResourceFile("weapon_gui_de.properties", WeaponsGUI_DE.class);
        Service.INSTANCE.createTableFromResourceFile("weapon_gui_fr.properties", WeaponsGUI_FR.class);
        Service.INSTANCE.createTableFromResourceFile("weapon_gui_ru.properties", WeaponsGUI_RU.class);
        LOGGER.info("Creating weapons GUI tables [complete]");
    }
}
