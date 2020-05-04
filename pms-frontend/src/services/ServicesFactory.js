import PmsService from "./PmsService";

const services = {
    pms : PmsService
};

export const ServicesFactory = {
    get : name => services[name]
};