import {FunctionComponent} from 'react';

/**
 * Prevent the screen from sleeping.
 */
export function activateKeepAwake(): void;

/**
 * Releases screen-sleep prevention.
 */
export function deactivateKeepAwake(): void;

/**
 * Alert screen when device is lock and turn the screen on.
 */
 export function activateLockScreen(): void;

 /**
  * Releases screen lock and CPU using power manager.
  */
 export function deactivateLockScreen(): void;

/**
 * React hook to keep the screen awake for as long as the owner component
 * is mounted.
 */
export function useKeepAwake(): void;

/**
 * React component to keep the screen awake while this component is rendered.
 */
declare const KeepAwake: FunctionComponent;
export default KeepAwake;
