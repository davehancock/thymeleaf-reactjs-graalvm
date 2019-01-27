import React from 'react';
import ReactDOM from 'react-dom';

import {Nav, NavItem, NavLink} from 'reactstrap'

import 'bootstrap/dist/css/bootstrap.min.css';

const initialState = window.nav_PRELOADED_STATE_ ? window.nav_PRELOADED_STATE_ : {};

ReactDOM.hydrate((
    <Nav>
        {initialState.navItems.map(function (navItemText) {
            return (
                <NavItem key={navItemText}>
                    <NavLink href={navItemText.toLowerCase()}>{navItemText}</NavLink>
                </NavItem>)
        })}
    </Nav>
), document.getElementById('nav'));
