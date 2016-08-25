package com.github.den13l.bloggerclient.utils;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class RxBus {

  private static final RxBus INSTANCE = new RxBus();

  private final Subject<Object, Object> subject = new SerializedSubject<>(PublishSubject.create());

  public static RxBus getInstance() {
    return INSTANCE;
  }

  @SuppressWarnings("unchecked")
  public <T> Subscription register(final Class<T> eventClass, Action1<T> onNext) {
    return subject.filter(event -> event.getClass().equals(eventClass))
        .map(obj -> (T) obj)
        .subscribe(onNext);
  }

  public void post(Object event) {
    subject.onNext(event);
  }

  public Observable<Object> toObserverable() {
    return subject;
  }
}