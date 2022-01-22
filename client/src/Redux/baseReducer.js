import {setBooksStore} from "./baseActions";
import {createReducer} from "@reduxjs/toolkit";
import intiState from "./baseState";

const reducer = createReducer(intiState,  {
    [setBooksStore]: (state, action) => {
        state.books = action.payload;
    }
})

export default reducer;