import React from 'react';
import ReactDOM from 'react-dom';

import PostCodeTable from '@daves125125/sample-react-component'

import '@daves125125/sample-react-component/dist/postcode.css'

const initialState = window.postcode_PRELOADED_STATE_ ? window.postcode_PRELOADED_STATE_ : {};

ReactDOM.hydrate((
    <PostCodeTable postcodes={initialState.postcodes}/>
), document.getElementById('postcode'));
