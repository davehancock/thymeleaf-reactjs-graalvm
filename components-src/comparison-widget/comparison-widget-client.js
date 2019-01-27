import React from 'react';
import ReactDOM from 'react-dom';

import {Comparison} from '@transferwise/comparison-components'

import '@transferwise/comparison-components/dist/comparison-components.css';

const initialState = window.comparison_widget_PRELOADED_STATE_ ? window.comparison_widget_PRELOADED_STATE_ : {};

ReactDOM.hydrate((
    <Comparison
        source={initialState.comparisonProps.source}
        target={initialState.comparisonProps.target}
        amount={initialState.comparisonProps.amount}
        quotes={initialState.comparisonProps.quotes}
        route={initialState.comparisonProps.rateRoute}
    />
), document.getElementById('comparison_widget'));
