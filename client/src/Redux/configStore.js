import { applyMiddleware, createStore } from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';

import reducer from "./baseReducer";

export const setStore = (preloadedState) => {
    const middlewareEnhancer = applyMiddleware();
    const enhancers = [middlewareEnhancer];
    const composeEnhancers = composeWithDevTools({ name: 'Library Management' });
    const composedEnhancers = composeEnhancers(...enhancers);
    return createStore(reducer, preloadedState, composedEnhancers);
}