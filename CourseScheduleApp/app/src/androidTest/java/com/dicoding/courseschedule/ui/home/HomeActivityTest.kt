import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.dicoding.courseschedule.ui.home.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.ui.add.AddCourseActivity
import org.junit.Before

@LargeTest
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Before
    fun setup(){
        ActivityScenario.launch(HomeActivity::class.java)
        Intents.init()
    }

    @Test
    fun homeActivityTest() {
        Espresso.onView(withId(R.id.action_add)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.action_add)).perform(click())
        Intents.intended(IntentMatchers.hasComponent(AddCourseActivity::class.qualifiedName))
        Espresso.onView(withId(R.id.ed_course_name)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.ed_note)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.ed_lecturer)).check(matches(isDisplayed()))
    }
}
