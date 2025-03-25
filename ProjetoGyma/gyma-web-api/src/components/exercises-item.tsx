import CrudDropdown from "./crud-dropdown";

interface ExercisesItemProps {
    exercise: Exercise
}

export default function ExercisesItem( {exercise}: ExercisesItemProps ){
    return (
        <div className="flex justify-between my-4">
            <div className="flex gap-2">
                <span>{exercise.name}</span>
            </div>

            <div>
                <CrudDropdown />
            </div>
        </div>
    )
}