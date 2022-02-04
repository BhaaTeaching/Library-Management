import {setBooksStore, setUserStore} from "./baseActions";
import {createReducer} from "@reduxjs/toolkit";
import intiState from "./baseState";

const reducer = createReducer(intiState,  {
    [setBooksStore]: (state, action) => {
        state.books = action.payload;
    },
    [setUserStore]: (state, action) => {
        state.user = action.payload;
    }
})

export default reducer;