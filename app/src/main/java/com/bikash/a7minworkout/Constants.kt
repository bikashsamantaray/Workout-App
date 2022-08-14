package com.bikash.a7minworkout

object Constants {
    fun defaultExerciseList(): ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()
        val jumpingJack = ExerciseModel(
            1,"jumping jack",R.drawable.ic_jumping_jack,false,false)
        exerciseList.add(jumpingJack)

        val abdominalCrunch = ExerciseModel(
            2,"abdominal crunch",R.drawable.ic_abdominal_crunch,false,false)
        exerciseList.add(abdominalCrunch)

        val highKnee = ExerciseModel(
            3,"high knee",R.drawable.ic_high_knee,false,false)
        exerciseList.add(highKnee)

        val highKneesRunning = ExerciseModel(
            4,"high knees running in place",R.drawable.ic_high_knees_running_in_place,false,false)
        exerciseList.add(highKneesRunning)

        val lunge = ExerciseModel(
            5,"lunge",R.drawable.ic_lunge,false,false)
        exerciseList.add(lunge)

        val pushUpAndRotation = ExerciseModel(
            6,"push up and rotation",R.drawable.ic_push_up_and_rotation,false,false)
        exerciseList.add(pushUpAndRotation)

        val pushUps = ExerciseModel(
            7,"Push ups",R.drawable.ic_pushups,false,false)
        exerciseList.add(pushUps)

        val sidePlank = ExerciseModel(
            8,"side plank",R.drawable.ic_side_plank,false,false)
        exerciseList.add(sidePlank)

        val stepUpUntoChair = ExerciseModel(
            9,"step up unto chair",R.drawable.ic_step_up_onto_chair,false,false)
        exerciseList.add(stepUpUntoChair)

        val tricepsDipOnChair = ExerciseModel(
            10,"triceps dips on chair",R.drawable.ic_triceps_dip_on_chair,false,false)
        exerciseList.add(tricepsDipOnChair)


        val wallSit = ExerciseModel(
            11,"wall sit",R.drawable.ic_wall_sit,false,false)
        exerciseList.add(wallSit)

        val squat = ExerciseModel(
            12,"squat",R.drawable.squat,false,false)
        exerciseList.add(squat)



        return exerciseList

    }
}