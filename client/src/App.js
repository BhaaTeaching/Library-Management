import './App.css';
import LibraryMainLayout from "./Components/MainPage/LibraryMainLayout";
import {BrowserRouter as Router} from "react-router-dom";
import {Provider} from 'react-redux';
import {setStore} from "./Redux/configStore";

function App() {
    return (
        <div className="App">
            <header className="App-header">
                <Provider store={setStore()}>
                    <Router>
                        {/*<AddBook/>*/}
                        <LibraryMainLayout/>
                    </Router>
                </Provider>
            </header>
        </div>
    );
}

export default App;
