#!/usr/bin/env bash
kubectl apply -f user-service/kubernetes/deployment.yml -n tutorial
kubectl apply -f user-service/kubernetes/service.yml -n tutorial
kubectl apply -f <(istioctl kube-inject -f online-store-web/kubernetes/deployment.yml) -n tutorial
kubectl apply -f online-store-web/kubernetes/service.yml -n tutorial
kubectl apply -f <(istioctl kube-inject -f catalog-service/kubernetes/deployment.yml) -n tutorial
kubectl apply -f catalog-service/kubernetes/service.yml -n tutorial
kubectl apply -f <(istioctl kube-inject -f payment-service/kubernetes/deployment.yml) -n tutorial
kubectl apply -f payment-service/kubernetes/service.yml -n tutorial
kubectl apply -f <(istioctl kube-inject -f edge-service/kubernetes/deployment.yml) -n tutorial
kubectl apply -f edge-service/kubernetes/service.yml -n tutorial
kubectl apply -f <(istioctl kube-inject -f account-service/kubernetes/deployment.yml) -n tutorial
kubectl apply -f account-service/kubernetes/service.yml -n tutorial
kubectl apply -f <(istioctl kube-inject -f order-service/kubernetes/deployment.yml) -n tutorial
kubectl apply -f order-service/kubernetes/service.yml -n tutorial
kubectl apply -f <(istioctl kube-inject -f inventory-service/kubernetes/deployment.yml) -n tutorial
kubectl apply -f inventory-service/kubernetes/service.yml -n tutorial
kubectl apply -f <(istioctl kube-inject -f shopping-cart-service/kubernetes/deployment.yml) -n tutorial
kubectl apply -f shopping-cart-service/kubernetes/service.yml -n tutorial
