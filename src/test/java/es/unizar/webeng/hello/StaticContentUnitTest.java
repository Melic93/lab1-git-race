package es.unizar.webeng.hello;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StaticContentUnitTest {

    @Value("${app.message:Hello World}")
    private String message;

    @InjectMocks
    private HelloController controller = new HelloController();

    private MockMvc mockMvc;

    private InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    /**
     * These method is going to be called before each test
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        // The method build now is a mock function
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver()).build();
    }

    /**
     * Check the calling GET("/")
     */
    @Test
    public void testMessage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // /**
    //  * Checks the method adding a movie
    //  */
    // @Test
    // public void addAMovie() throws Exception {
    //     this.mockMvc.perform(post("/add")
    //             .content("wlalala"))
                
    //             .andExpect(status().isOk());
    // }



    
    // @Test
    // @Throws(Exception::class)
    // fun `test add a movie`() {
    //     println("JAJA")
    //     val request = controller.add("1990", "Terminator")
    //     // println(status)
    //     assertThat(request.getStatusCode(), `is`(HttpStatus.OK))
    // }

    // /**
    //  * Check method get all movies
    //  */
    // @Test
    // @Throws(Exception::class)
    // fun `test get all movies`() {
    //     val request = controller.findAll()
    //     assertThat(request.get("1990"), `is`("Terminator"))
    // }

}
