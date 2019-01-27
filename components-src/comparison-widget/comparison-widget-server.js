import React from 'react';
import ReactDOMServer from 'react-dom/server'
import serialize from 'serialize-javascript';

import {Comparison} from '@transferwise/comparison-components'

global.render = (template, componentName, serverSideState) => {

    const initialState = {
        comparisonProps: JSON.parse(serverSideState)
    };

    const markup = ReactDOMServer.renderToString(
        <Comparison
            source={initialState.comparisonProps.source}
            target={initialState.comparisonProps.target}
            amount={initialState.comparisonProps.amount}
            quotes={initialState.comparisonProps.quotes}
            route={initialState.comparisonProps.rateRoute}
        />
    );

    const normalisedComponentName = componentName.replace(/-/g, "_");
    return template
        .replace(new RegExp(/{COMPONENT_NAME}/g), normalisedComponentName)
        .replace('{SERVER_RENDERED_HTML}', markup)
        .replace('{SERVER_RENDERED_STATE}', serialize(initialState, {isJSON: true}));
};
