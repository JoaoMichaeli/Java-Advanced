import CrudDropdown from "./crud-dropdown";
import Icon from "./icon";

interface ExercisesItemProps {
    exercise: Exercise
}

export default function ExercisesItem( {exercise}: ExercisesItemProps ){
    return (
        <div className="flex justify-between my-4 text-white">
            <div className="flex gap-2 ">
                <Icon name={exercise.icon} />
                <span>{exercise.name}</span>
            </div>

            <div>
                <CrudDropdown />
            </div>
        </div>
    )
}