package com.gtnewhorizon.newgunrizons.util;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public class ClassInfo {

    @Getter
    private final String notchClassName;
    @Getter
    private final String mcpClassName;
    private final Map<ClassInfo.MethodSignature, String> notchMethodInfoMap = new HashMap<>();
    private final Map<ClassInfo.MethodSignature, String> notchSignatureMap = new HashMap<>();
    private final Map<ClassInfo.MethodSignature, String> mcpMethodInfoMap = new HashMap<>();

    public ClassInfo(String mcpClassName, String notchClassName) {
        this.mcpClassName = mcpClassName;
        this.notchClassName = notchClassName;
    }

    public ClassInfo addMethodInfo(String mcpMethodName, String signature, String notchName) {
        this.notchMethodInfoMap.put(new ClassInfo.MethodSignature(mcpMethodName, signature), notchName);
        return this;
    }

    public ClassInfo addMethodInfo2(String mcpMethodName, String signature, String notchName, String notchSignature) {
        this.notchMethodInfoMap.put(new ClassInfo.MethodSignature(mcpMethodName, signature), notchName);
        this.notchSignatureMap.put(new ClassInfo.MethodSignature(mcpMethodName, signature), notchSignature);
        return this;
    }

    public ClassInfo addMethodInfo(String genericMethodName, String mcpMethodName, String signature, String notchName) {
        this.mcpMethodInfoMap.put(new ClassInfo.MethodSignature(genericMethodName, signature), mcpMethodName);
        this.notchMethodInfoMap.put(new ClassInfo.MethodSignature(genericMethodName, signature), notchName);
        return this;
    }

    public boolean classMatches(String className) {
        String normalizedClassName = className.replace('.', '/');
        return this.mcpClassName.equals(normalizedClassName) || this.notchClassName.equals(normalizedClassName);
    }

    public boolean methodMatches(String expectedMcpMethodName, String expectedMcpMethodSignature,
        String methodOwnerClassName, String methodName, String methodSignature) {
        if (!expectedMcpMethodSignature.equals(methodSignature) && !methodSignature.equals(
            this.notchSignatureMap
                .get(new ClassInfo.MethodSignature(expectedMcpMethodName, expectedMcpMethodSignature)))) {
            return false;
        } else if (this.mcpClassName.equals(methodOwnerClassName)) {
            return expectedMcpMethodName.equals(methodName) || methodName.equals(
                this.mcpMethodInfoMap
                    .get(new ClassInfo.MethodSignature(expectedMcpMethodName, expectedMcpMethodSignature)));
        } else if (!this.notchClassName.equals(methodOwnerClassName)) {
            return false;
        } else {
            String notchMethodName = this.notchMethodInfoMap
                .get(new ClassInfo.MethodSignature(expectedMcpMethodName, expectedMcpMethodSignature));
            return methodName.equals(notchMethodName);
        }
    }

    private static class MethodSignature {

        String name;
        String signature;

        MethodSignature(String name, String signature) {
            this.name = name;
            this.signature = signature;
        }

        public int hashCode() {
            int result = 31 + (this.name == null ? 0 : this.name.hashCode());
            result = 31 * result + (this.signature == null ? 0 : this.signature.hashCode());
            return result;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            } else if (obj == null) {
                return false;
            } else if (this.getClass() != obj.getClass()) {
                return false;
            } else {
                ClassInfo.MethodSignature other = (ClassInfo.MethodSignature) obj;

                if (this.name == null) {
                    if (other.name != null) {
                        return false;
                    }
                } else if (!this.name.equals(other.name)) {
                    return false;
                }

                if (this.signature == null) return other.signature == null;
                else return this.signature.equals(other.signature);
            }
        }
    }
}
