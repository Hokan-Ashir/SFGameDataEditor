package sfgamedataeditor.database.spells.parameters;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Requirements {
    private static final Integer SIZE = 3;
    private List<Integer> requirementClasses = new ArrayList<>(SIZE);
    private List<Integer> requirementSubClasses = new ArrayList<>(SIZE);

    public Requirements() {
    }

    public Requirements(int requirementClass0,
                        int requirementSubClass0,
                        int requirementClass1,
                        int requirementSubClass1,
                        int requirementClass2,
                        int requirementSubClass2) {
        addRequirementClass(requirementClass0);
        addRequirementSubClass(requirementSubClass0);

        addRequirementClass(requirementClass1);
        addRequirementSubClass(requirementSubClass1);

        addRequirementClass(requirementClass2);
        addRequirementSubClass(requirementSubClass2);
    }


    public void addRequirementClass(Integer value) {
        requirementClasses.add(value);
    }

    public void addRequirementSubClass(Integer value) {
        requirementSubClasses.add(value);
    }

    public Integer getRequirementClass(int position) {
        return requirementClasses.get(position);
    }

    public Integer getSubRequirementClass(int position) {
        return requirementSubClasses.get(position);
    }

    public void fillRestSubClassesValuesWithNulls() {
        for (int i = 0; i <= requirementClasses.size(); ++i) {
            if (requirementSubClasses.size() < i) {
                requirementSubClasses.add(0);
            }
        }
    }

    @Override
    protected Requirements clone() {
        Requirements newObject = new Requirements();
        for (int i = 0; i < requirementClasses.size(); ++i) {
            newObject.addRequirementClass(requirementClasses.get(i));
            newObject.addRequirementSubClass(requirementSubClasses.get(i));
        }

        return newObject;
    }

    @Override
    public boolean equals(Object obj) {
        Requirements that = (Requirements) obj;
        EqualsBuilder builder = new EqualsBuilder();
        for (int i = 0; i < SIZE; i++) {
            builder.append(this.getRequirementClass(i), that.getRequirementClass(i));
            builder.append(this.getSubRequirementClass(i), that.getSubRequirementClass(i));
        }

        return builder.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        for (Integer requirementClass : requirementClasses) {
            builder.append(requirementClass);
        }

        for (Integer requirementSubClass : requirementSubClasses) {
            builder.append(requirementSubClass);
        }

        return builder.hashCode();
    }

    public static Set<Requirements> generatePermutations(Requirements requirements) {
        Integer requirementClass0 = requirements.getRequirementClass(0);
        Integer subRequirementClass0 = requirements.getSubRequirementClass(0);

        Integer requirementClass1 = requirements.getRequirementClass(1);
        Integer subRequirementClass1 = requirements.getSubRequirementClass(1);

        Integer requirementClass2 = requirements.getRequirementClass(2);
        Integer subRequirementClass2 = requirements.getSubRequirementClass(2);

        Set<Requirements> requirementsSet = new HashSet<>();
        requirementsSet.add(new Requirements(requirementClass0, subRequirementClass0, requirementClass1, subRequirementClass1, requirementClass2, subRequirementClass2));
        requirementsSet.add(new Requirements(requirementClass0, subRequirementClass0, requirementClass2, subRequirementClass2, requirementClass1, subRequirementClass1));
        requirementsSet.add(new Requirements(requirementClass1, subRequirementClass1, requirementClass0, subRequirementClass0, requirementClass2, subRequirementClass2));
        requirementsSet.add(new Requirements(requirementClass1, subRequirementClass1, requirementClass2, subRequirementClass2, requirementClass0, subRequirementClass0));
        requirementsSet.add(new Requirements(requirementClass2, subRequirementClass2, requirementClass0, subRequirementClass0, requirementClass1, subRequirementClass1));
        requirementsSet.add(new Requirements(requirementClass2, subRequirementClass2, requirementClass1, subRequirementClass1, requirementClass0, subRequirementClass0));

        return requirementsSet;
    }

    public static Set<Requirements> getAllRequirements(Requirements requirements) {
        // add subClasses requirements if they are not exists in all cases
        requirements.fillRestSubClassesValuesWithNulls();
        // cause of stripping duplicated School names (for nice UI) and so called "Other" school as well
        // we need to recreate all possible cases of such names stripping
        Set<Requirements> result = new HashSet<>();
        if (requirements.requirementClasses.size() == SIZE) {
            // situation when user selected school "Black magic + Heavy Combat Arts + Archery"
            result.add(requirements);
        } else if (requirements.requirementClasses.size() == SIZE - 1) {
            requirements.fillRestSubClassesValuesWithNulls();
            // situation when user selected school "Black magic + Heavy Combat Arts"
            // before stripping it can be "BM + BM + HCA"/ "BM + HCA + HCA"/ "BM + HCA + Other"
            Integer requirementClass1 = requirements.getRequirementClass(0);
            Integer subRequirementClass1 = requirements.getSubRequirementClass(0);

            Integer requirementClass2 = requirements.getRequirementClass(1);
            Integer subRequirementClass2 = requirements.getSubRequirementClass(1);

            Requirements requirements1 = requirements.clone();
            Requirements requirements2 = requirements.clone();

            requirements.addRequirementClass(requirementClass1);
            requirements.addRequirementSubClass(subRequirementClass1);

            requirements1.addRequirementClass(requirementClass2);
            requirements1.addRequirementSubClass(subRequirementClass2);

            requirements2.addRequirementClass(0);
            requirements2.addRequirementSubClass(0);

            result.add(requirements);
            result.add(requirements1);
            result.add(requirements2);
        } else {
            // situation when user selected school "Black magic"
            // before stripping it can be "BM + BM + BM"/ "BM + BM + Other"/ "BM + Other + Other"
            Integer requirementClass = requirements.getRequirementClass(0);
            Integer subRequirementClass = requirements.getSubRequirementClass(0);

            Requirements requirements1 = requirements.clone();
            Requirements requirements2 = requirements.clone();

            requirements.addRequirementClass(requirementClass);
            requirements.addRequirementSubClass(subRequirementClass);
            requirements.addRequirementClass(requirementClass);
            requirements.addRequirementSubClass(subRequirementClass);

            requirements1.addRequirementClass(requirementClass);
            requirements1.addRequirementSubClass(subRequirementClass);
            requirements1.addRequirementClass(0);
            requirements1.addRequirementSubClass(0);

            requirements2.addRequirementClass(0);
            requirements2.addRequirementSubClass(0);
            requirements2.addRequirementClass(0);
            requirements2.addRequirementSubClass(0);

            result.add(requirements);
            result.add(requirements1);
            result.add(requirements2);
        }

        Set<Requirements> set = new HashSet<>();
        for (Requirements requirements1 : result) {
            Set<Requirements> requirementsSet = Requirements.generatePermutations(requirements1);
            set.addAll(requirementsSet);
        }
        return set;
    }
}
