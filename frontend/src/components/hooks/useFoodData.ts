import axios, { Axios, AxiosPromise } from "axios"
import { FoodData } from "../interface/FoodData";
import { useQuery } from "@tanstack/react-query";

const API_URL = "http://localhost:8080";

const fetchData = async (): AxiosPromise<FoodData> => {
    const response = axios.get(API_URL + "/cardapio");
    return response;
}
export function useFoodData() {
    const query = useQuery({
        queryFn: fetchData,
        queryKey: ["food-data"],
        retry: 100
    })

    return {
        ...query,
        data:query.data?.data
    }
}