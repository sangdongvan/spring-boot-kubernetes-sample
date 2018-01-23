#!/usr/bin/env bash

# ----------------------------------------------------------------------------
# 1. System requirements
# ----------------------------------------------------------------------------

cat <<EOF > /etc/modules-load.d/overlay.conf
overlay
EOF

cat <<EOF > /etc/sysconfig/docker-storage-setup
STORAGE_DRIVER="overlay"
EOF

cat <<EOF >  /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
EOF
sysctl --system

# ----------------------------------------------------------------------------
# 2. Install Docker
# ----------------------------------------------------------------------------
#!/usr/bin/env bash

yum remove -y docker docker-common docker-selinux docker-engine
yum install -y yum-utils device-mapper-persistent-data lvm2
yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
yum install -y docker-ce-17.12.0.ce

# ----------------------------------------------------------------------------
# 3. Install Kuberntes
# ----------------------------------------------------------------------------

cat <<EOF > /etc/yum.repos.d/kubernetes.repo
[kubernetes]
name=Kubernetes
baseurl=https://packages.cloud.google.com/yum/repos/kubernetes-el7-x86_64
enabled=1
gpgcheck=1
repo_gpgcheck=1
gpgkey=https://packages.cloud.google.com/yum/doc/yum-key.gpg https://packages.cloud.google.com/yum/doc/rpm-package-key.gpg
EOF
setenforce 0
yum install -y kubelet kubectl kubeadm

# ----------------------------------------------------------------------------
# 3. Configure kubelet's cgroup-driver to match with Docker
# ----------------------------------------------------------------------------

sed -i 's=--cgroup-driver\=systemd=--cgroup-driver\=cgroupfs=g' /etc/systemd/system/kubelet.service.d/10-kubeadm.conf
systemctl daemon-reload

# ----------------------------------------------------------------------------
# 4. Restart services
# ----------------------------------------------------------------------------

systemctl enable docker && systemctl start docker
systemctl enable kubelet && systemctl start kubelet

# ----------------------------------------------------------------------------
# 5. Configure network, flannel plugin
# ----------------------------------------------------------------------------

# @ref: https://github.com/coreos/flannel/blob/master/Documentation/kubernetes.md
kubeadm init --pod-network-cidr=10.244.0.0/16

mkdir -p $HOME/.kube
cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
chown $(id -u):$(id -g) $HOME/.kube/config

kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/v0.9.1/Documentation/kube-flannel.yml
