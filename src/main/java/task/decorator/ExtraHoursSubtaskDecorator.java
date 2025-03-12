package task.decorator;

import task.model.ISubtask;

public class ExtraHoursSubtaskDecorator extends SubtaskDecorator {
    private int extraHours;
    public ExtraHoursSubtaskDecorator(ISubtask decoratedSubtask, int extraHours) {
        super(decoratedSubtask);
        this.setExtraHours(extraHours);
    }

    public int getExtraHours() {
        return extraHours;
    }

    public void setExtraHours(int extraHours) {
        if(extraHours < 0) {
            throw new IllegalArgumentException("Horas extras não podem ser negativas");
        }
        this.extraHours = extraHours;
    }

    @Override
    public int getHoursNeeded() {
        return super.getHoursNeeded() + extraHours;
    }
}
