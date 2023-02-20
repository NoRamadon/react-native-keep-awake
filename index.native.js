import React, { useEffect } from "react";
import { NativeModules } from "react-native";

export const activateKeepAwake = () => {
  NativeModules.KCKeepAwake.activate();
};

export const deactivateKeepAwake = () => {
  NativeModules.KCKeepAwake?.deactivate();
};

export const activateLockScreen = () => {
  NativeModules.KCKeepAwake?.activateLockScreen();
};

export const deactivateLockScreen = () => {
  NativeModules.KCKeepAwake?.deactivateLockScreen();
};

export const useKeepAwake = () => {
  useEffect(() => {
    activateKeepAwake();
  }, []);
};

export default () => {
  useEffect(() => {
    activateKeepAwake();
    return deactivateKeepAwake;
  }, []);

  return null;
};
