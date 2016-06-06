package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sergey on 14.05.2016.
 */
public class Users extends ForwardingSet<UserData> {

  private Set<UserData> delegate;

 public Users() {
    this.delegate = new HashSet<UserData>();
  }

  @Override
  protected Set<UserData> delegate() {
    return delegate;
  }


}
