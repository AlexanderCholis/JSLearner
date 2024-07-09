package eu.tkacas.jslearner.domain.usecase.main.roadmap

import eu.tkacas.jslearner.domain.model.roadmap.RoadMapNodeCategory
import eu.tkacas.jslearner.domain.model.roadmap.RoadMapNodePosition
import eu.tkacas.jslearner.domain.model.roadmap.RoadMapNodeState
import eu.tkacas.jslearner.domain.model.roadmap.RoadMapNodeStatus
import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.domain.repository.RoadMapRepository
import eu.tkacas.jslearner.data.model.CourseLevel
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel

class GetRoadMapUseCase(
    private val roadMapRepository: RoadMapRepository,
    private val authRepository: AuthRepository
) {

    suspend fun execute(): List<RoadMapNodeState> {
        val courses = roadMapRepository.getCourses()
        val completedLessons = authRepository.getUserCompletedLessons().toSet()
        val userExperienceLevel = authRepository.getUserStats().experienceLevel
        val roadMapNodes = mutableListOf<RoadMapNodeState>()

        for ((index, course) in courses.withIndex()) {
            val lessons = roadMapRepository.getLessons(course.id)
            val completedCourseLessons = completedLessons // Assuming completedLessons is a Set<String> of lesson IDs
            val isCourseCompleted = lessons.all { lesson -> completedCourseLessons.contains(lesson.id) }
            val isCourseInProgress = completedCourseLessons.isNotEmpty() && !isCourseCompleted

            val previousCoursesCompleted = if (index == 0) true else {
                courses.take(index).all { previousCourse ->
                    roadMapRepository.getLessons(previousCourse.id).all { lesson ->
                        completedCourseLessons.contains(lesson.id)
                    }
                }
            }

            val courseStatus = when {
                isCourseCompleted -> RoadMapNodeStatus.COMPLETED
                isCourseInProgress -> RoadMapNodeStatus.IN_PROGRESS
                previousCoursesCompleted && isExperienceLevelMatch(course.level, userExperienceLevel) -> RoadMapNodeStatus.UNLOCKED
                else -> RoadMapNodeStatus.LOCKED
            }

            roadMapNodes.add(RoadMapNodeState(
                id = course.id,
                status = courseStatus,
                position = RoadMapNodePosition.MIDDLE,
                category = RoadMapNodeCategory.COURSE,
                title = course.title))

            for ((lessonIndex, lesson) in lessons.withIndex()) {
                val isLessonCompleted = completedCourseLessons.contains(lesson.id)
                val previousLessonsCompleted = if (lessonIndex == 0) true else {
                    val previousLesson = lessons[lessonIndex - 1]
                    completedCourseLessons.contains(previousLesson.id)
                }

                val lessonStatus = when {
                    isLessonCompleted -> RoadMapNodeStatus.COMPLETED
                    previousLessonsCompleted && courseStatus == RoadMapNodeStatus.UNLOCKED && isExperienceLevelMatch(course.level, userExperienceLevel) -> RoadMapNodeStatus.UNLOCKED
                    else -> RoadMapNodeStatus.LOCKED
                }

                val position = if (index == courses.lastIndex && lessonIndex == lessons.lastIndex) {
                    RoadMapNodePosition.LAST
                } else {
                    RoadMapNodePosition.MIDDLE
                }

                roadMapNodes.add(RoadMapNodeState(
                    id = lesson.id,
                    status = lessonStatus,
                    position = position,
                    category = RoadMapNodeCategory.LESSON,
                    title = lesson.title))
            }
        }

        return roadMapNodes
    }

    private fun isExperienceLevelMatch(courseLevel: CourseLevel, experienceLevel: ExperienceLevel?): Boolean {
        return when (courseLevel) {
            CourseLevel.BEGINNER -> true
            CourseLevel.INTERMEDIATE -> experienceLevel == ExperienceLevel.SOME_EXPERIENCE || experienceLevel == ExperienceLevel.A_LOT_OF_EXPERIENCE
            CourseLevel.ADVANCED -> experienceLevel == ExperienceLevel.A_LOT_OF_EXPERIENCE
        }
    }
}
