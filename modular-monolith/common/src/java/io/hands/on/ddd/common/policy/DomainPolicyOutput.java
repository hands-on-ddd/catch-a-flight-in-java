package io.hands.on.ddd.common.policy;

/**
 * Domain policy output sealed types.
 */
public sealed interface DomainPolicyOutput {
    record Allowance() implements DomainPolicyOutput {}
    record Rejection(String reason) implements DomainPolicyOutput {}
}
