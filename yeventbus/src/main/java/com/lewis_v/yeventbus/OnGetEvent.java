package com.lewis_v.yeventbus;

import java.util.Observer;

interface OnGetEvent<E extends IEvent> extends Observer{
    void onSuccess(E event);
    void onFail(Exception e);
}
