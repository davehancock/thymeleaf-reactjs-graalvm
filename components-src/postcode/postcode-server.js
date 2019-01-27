import React from 'react';
import ReactDOMServer from 'react-dom/server'
import serialize from 'serialize-javascript';

import PostCodeTable from '@daves125125/sample-react-component'

global.render = (template, componentName, serverSideState) => {

    const initialState = {
        postcodes: JSON.parse(serverSideState)
    };

    const markup = ReactDOMServer.renderToString(
        <PostCodeTable postcodes={initialState.postcodes}/>
    );

    const normalisedComponentName = componentName.replace(/-/g, "_");
    return template
        .replace(new RegExp(/{COMPONENT_NAME}/g), normalisedComponentName)
        .replace('{SERVER_RENDERED_HTML}', markup)
        .replace('{SERVER_RENDERED_STATE}', serialize(initialState, {isJSON: true}));
};
