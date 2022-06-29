package io.mars.et.xiaoyi.services;

import io.mars.et.xiaoyi.domains.views.ConcertCartView;

public interface ConcertOrderService {
  ConcertCartView createConcertCart(int concertId);

  List<ConcertOrderItemView>
}
