package com.example.restaurant.rest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

public class RestaurantApplication extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> set = new HashSet<>();
    set.add(CustomerRestForm.class);
    return set;
  }

  @Override
  public Set<Object> getSingletons() {
    return Collections.emptySet();
  }
}
