package org.example.web;

import org.example.web.converter.ProtocolBufferHttpMessageConverter;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Integration tests for a controller.
 */
@ContextConfiguration(locations = { "classpath:springDataContext.xml", "classpath:springServiceContext.xml", "classpath:springWebContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class ControllerTest
{
  protected MockMvc mock;

  /**
   * Sets up beans required for the test to run.
   */
  @Before
  public void setup()
  {
    final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("/page/");
    viewResolver.setSuffix(".html");

    mock = MockMvcBuilders.standaloneSetup(controller())
        .setMessageConverters(new MappingJackson2HttpMessageConverter()
            , new Jaxb2RootElementHttpMessageConverter()
            , new ProtocolBufferHttpMessageConverter())
        .setViewResolvers(viewResolver)
        .build();
  }

  /**
   * Gets an instance of the controller to test.
   *
   * @return An instance of the controller to test.
   */
  protected abstract Object controller();
}
