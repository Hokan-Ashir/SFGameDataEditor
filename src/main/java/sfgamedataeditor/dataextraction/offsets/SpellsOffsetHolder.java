package sfgamedataeditor.dataextraction.offsets;

import sfgamedataeditor.files.FilesContainer;
import sfgamedataeditor.utils.Pair;

public class SpellsOffsetHolder extends AbstractOffsetHolder {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setDataLength() {
        setDataLength(0x4c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillOffsets() {
        boolean isVersion11 = FilesContainer.INSTANCE.isVersion11();

        if (isVersion11) {
            // TODO find appropriate offset (instead of 0x3fd13)
            addOffsetPair(new Pair<>(0x20, 0x3fd13));
        } else {
//            addOffsetPair(new Pair<>(32, 63188));
//            addOffsetPair(new Pair<>(69648, 73296));
//            addOffsetPair(new Pair<>(81504, 86976));
//            addOffsetPair(new Pair<>(88800, 89712));
//            addOffsetPair(new Pair<>(90624, 144128));
//            addOffsetPair(new Pair<>(144736, 149676));
//            addOffsetPair(new Pair<>(150284, 161228));
//            addOffsetPair(new Pair<>(161836, 162444));
//            addOffsetPair(new Pair<>(163052, 163660));
//            addOffsetPair(new Pair<>(164268, 164876));
//            addOffsetPair(new Pair<>(165484, 166092));
//            addOffsetPair(new Pair<>(166700, 167308));
//            addOffsetPair(new Pair<>(167916, 168524));
//            addOffsetPair(new Pair<>(169132, 169740));
//            addOffsetPair(new Pair<>(170348, 170956));
//            addOffsetPair(new Pair<>(171564, 172172));
//            addOffsetPair(new Pair<>(172780, 173388));
//            addOffsetPair(new Pair<>(173996, 174604));
//            addOffsetPair(new Pair<>(175212, 175820));
//            addOffsetPair(new Pair<>(176428, 184332));
//            addOffsetPair(new Pair<>(184940, 189804));
//            addOffsetPair(new Pair<>(190412, 201356));
//            addOffsetPair(new Pair<>(201964, 213136));
//            addOffsetPair(new Pair<>(213744, 221648));
//            addOffsetPair(new Pair<>(221670, 221898));
//            addOffsetPair(new Pair<>(221876, 231072));
//            addOffsetPair(new Pair<>(231604, 246044));
//            addOffsetPair(new Pair<>(253644, 261396));
//            addOffsetPair(new Pair<>(409941, 410017));
//            addOffsetPair(new Pair<>(411415, 411491));
//            addOffsetPair(new Pair<>(415089, 415165));
//            addOffsetPair(new Pair<>(417883, 417959));
//            addOffsetPair(new Pair<>(418477, 418553));
//            addOffsetPair(new Pair<>(418631, 418707));
//            addOffsetPair(new Pair<>(419247, 419323));
//            addOffsetPair(new Pair<>(427164, 427240));
//            addOffsetPair(new Pair<>(432557, 432633));
//            addOffsetPair(new Pair<>(435615, 435691));
//            addOffsetPair(new Pair<>(439971, 440047));
//            addOffsetPair(new Pair<>(465161, 465237));
//            addOffsetPair(new Pair<>(565104, 565180));
//            addOffsetPair(new Pair<>(570610, 570686));
//            addOffsetPair(new Pair<>(572844, 572920));
//            addOffsetPair(new Pair<>(576624, 576700));
//            addOffsetPair(new Pair<>(579864, 579940));
//            addOffsetPair(new Pair<>(581052, 581128));
//            addOffsetPair(new Pair<>(581448, 581524));
//            addOffsetPair(new Pair<>(581870, 581946));
//            addOffsetPair(new Pair<>(1314703, 1314779));
//            addOffsetPair(new Pair<>(66421354, 66421430));
//            addOffsetPair(new Pair<>(66443120, 66443196));
//            addOffsetPair(new Pair<>(66443184, 66443260));
//            addOffsetPair(new Pair<>(66443248, 66443324));
//            addOffsetPair(new Pair<>(66443312, 66443388));
//            addOffsetPair(new Pair<>(66443376, 66443452));
//            addOffsetPair(new Pair<>(66443440, 66443516));
//            addOffsetPair(new Pair<>(66460528, 66460604));
//            addOffsetPair(new Pair<>(66466288, 66466364));
//            addOffsetPair(new Pair<>(66466352, 66466428));
//            addOffsetPair(new Pair<>(66466992, 66467068));
//            addOffsetPair(new Pair<>(66467120, 66467196));
//            addOffsetPair(new Pair<>(66467440, 66467516));
//            addOffsetPair(new Pair<>(66474224, 66474300));
//            addOffsetPair(new Pair<>(66476528, 66476604));
//            addOffsetPair(new Pair<>(66476592, 66476668));
//            addOffsetPair(new Pair<>(66476656, 66476732));
//            addOffsetPair(new Pair<>(66476720, 66476796));
//            addOffsetPair(new Pair<>(66476784, 66476860));
//            addOffsetPair(new Pair<>(66476848, 66476924));
//            addOffsetPair(new Pair<>(66476976, 66477052));
//            addOffsetPair(new Pair<>(66477040, 66477116));
//            addOffsetPair(new Pair<>(66477104, 66477180));
//            addOffsetPair(new Pair<>(66477168, 66477244));
//            addOffsetPair(new Pair<>(66477232, 66477308));
//            addOffsetPair(new Pair<>(66477296, 66477372));
//            addOffsetPair(new Pair<>(66477360, 66477436));
//            addOffsetPair(new Pair<>(66477424, 66477500));
//            addOffsetPair(new Pair<>(66477488, 66477564));
//            addOffsetPair(new Pair<>(66477552, 66477628));
//            addOffsetPair(new Pair<>(66477616, 66477692));
//            addOffsetPair(new Pair<>(66477680, 66477756));
//            addOffsetPair(new Pair<>(66477744, 66477820));
//            addOffsetPair(new Pair<>(66477808, 66477884));
//            addOffsetPair(new Pair<>(66477872, 66477948));
//            addOffsetPair(new Pair<>(66477936, 66478012));
//            addOffsetPair(new Pair<>(66480048, 66480124));
//            addOffsetPair(new Pair<>(66480112, 66480188));
//            addOffsetPair(new Pair<>(66480176, 66480252));
//            addOffsetPair(new Pair<>(66480240, 66480316));
//            addOffsetPair(new Pair<>(66480304, 66480380));
//            addOffsetPair(new Pair<>(66480368, 66480444));
//            addOffsetPair(new Pair<>(66480432, 66480508));
//            addOffsetPair(new Pair<>(66480496, 66480572));
//            addOffsetPair(new Pair<>(66480560, 66480636));
//            addOffsetPair(new Pair<>(66480624, 66480700));
//            addOffsetPair(new Pair<>(66480688, 66480764));
//            addOffsetPair(new Pair<>(66480752, 66480828));
//            addOffsetPair(new Pair<>(66480816, 66480892));
//            addOffsetPair(new Pair<>(66480880, 66480956));
//            addOffsetPair(new Pair<>(66480944, 66481020));
//            addOffsetPair(new Pair<>(66481008, 66481084));
//            addOffsetPair(new Pair<>(66481072, 66481148));
//            addOffsetPair(new Pair<>(66481136, 66481212));
//            addOffsetPair(new Pair<>(66481200, 66481276));
//            addOffsetPair(new Pair<>(66481264, 66481340));
//            addOffsetPair(new Pair<>(66481328, 66481404));
//            addOffsetPair(new Pair<>(66481392, 66481468));
//            addOffsetPair(new Pair<>(66481456, 66481532));
//            addOffsetPair(new Pair<>(66481520, 66481596));
//            addOffsetPair(new Pair<>(66481584, 66481660));
//            addOffsetPair(new Pair<>(66481648, 66481724));
//            addOffsetPair(new Pair<>(66481712, 66481788));
//            addOffsetPair(new Pair<>(66510192, 66510268));
//            addOffsetPair(new Pair<>(66510256, 66510332));
//            addOffsetPair(new Pair<>(66510320, 66510396));
//            addOffsetPair(new Pair<>(66510384, 66510460));
//            addOffsetPair(new Pair<>(66514288, 66514364));
//            addOffsetPair(new Pair<>(66515440, 66515516));
//            addOffsetPair(new Pair<>(66517360, 66517436));
//            addOffsetPair(new Pair<>(66518064, 66518140));
//            addOffsetPair(new Pair<>(66519920, 66519996));
//            addOffsetPair(new Pair<>(66519984, 66520060));
//            addOffsetPair(new Pair<>(66520048, 66520124));
//            addOffsetPair(new Pair<>(66520112, 66520188));
//            addOffsetPair(new Pair<>(66520176, 66520252));
//            addOffsetPair(new Pair<>(66520240, 66520316));
//            addOffsetPair(new Pair<>(66520304, 66520380));
//            addOffsetPair(new Pair<>(66520368, 66520444));
//            addOffsetPair(new Pair<>(66520432, 66520508));
//            addOffsetPair(new Pair<>(66520496, 66520572));
//            addOffsetPair(new Pair<>(66523568, 66523644));
//            addOffsetPair(new Pair<>(66523632, 66523708));
//            addOffsetPair(new Pair<>(66528624, 66528700));
//            addOffsetPair(new Pair<>(66532400, 66532476));
//            addOffsetPair(new Pair<>(66649562, 66649638));
//            addOffsetPair(new Pair<>(66649616, 66649692));
//            addOffsetPair(new Pair<>(66654476, 66654552));
//            addOffsetPair(new Pair<>(66654530, 66654606));
//            addOffsetPair(new Pair<>(66654584, 66654660));
//            addOffsetPair(new Pair<>(66654638, 66654714));
//            addOffsetPair(new Pair<>(66654692, 66654768));
//            addOffsetPair(new Pair<>(66654746, 66654822));
//            addOffsetPair(new Pair<>(66663604, 66663680));
//            addOffsetPair(new Pair<>(66664090, 66664166));
//            addOffsetPair(new Pair<>(66664252, 66664328));
//            addOffsetPair(new Pair<>(66664306, 66664382));
//            addOffsetPair(new Pair<>(66676670, 66676746));
//            addOffsetPair(new Pair<>(66677156, 66677232));
//            addOffsetPair(new Pair<>(66677696, 66677772));
//            addOffsetPair(new Pair<>(66680722, 66680798));
//            addOffsetPair(new Pair<>(66684826, 66684902));
//            addOffsetPair(new Pair<>(66856354, 66856430));
//            addOffsetPair(new Pair<>(66856626, 66856702));
//            addOffsetPair(new Pair<>(66857794, 66857870));
//
            addOffsetPair(new Pair<>(0x20, 0xf6d3));
            addOffsetPair(new Pair<>(0x1105c, 0x3fd13));
        }
    }
}
