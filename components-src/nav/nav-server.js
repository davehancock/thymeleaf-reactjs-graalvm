import React from 'react';
import ReactDOMServer from 'react-dom/server'
import serialize from 'serialize-javascript';

import {Nav, NavItem, NavLink} from 'reactstrap'

global.render = (template, componentName, serverSideState) => {

    const initialState = {
        navItems: JSON.parse(serverSideState)
    };

    const markup = ReactDOMServer.renderToString(
        <Nav>
            {initialState.navItems.map(function (navItemText) {
                return (
                    <NavItem key={navItemText}>
                        <NavLink href={navItemText.toLowerCase()}>{navItemText}</NavLink>
                    </NavItem>)
            })}
        </Nav>
    );

    const normalisedComponentName = componentName.replace(/-/g, "_");
    return template
        .replace(new RegExp(/{COMPONENT_NAME}/g), normalisedComponentName)
        .replace('{SERVER_RENDERED_HTML}', markup)
        .replace('{SERVER_RENDERED_STATE}', serialize(initialState, {isJSON: true}));
};
